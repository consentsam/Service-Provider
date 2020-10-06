package com.app.serviceprivider.activity.ui.service_mgmt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.serviceprivider.R
import com.app.serviceprivider.activity.ui.home.MainActivity
import com.app.serviceprivider.adapters.ServiceTypeAdapter
import com.app.serviceprivider.model.GetDeliveryResponse

import com.app.serviceprivider.utils.ErrorUtil
import com.app.serviceprivider.utils.SharePrefUtils
import com.app.serviceprivider.utils.getString
import com.singlevendor.base.NewBaseActivity
import com.singlevendor.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.activity_service_management.*

class ServiceManagementActivity : NewBaseActivity(),View.OnClickListener {

    lateinit var serviceViewModel: ServiceManagentViewModel
    var delivery_charges: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_management)

         init()
         initControl()
         myObserver()
    }


    override fun init() {

        serviceViewModel = ViewModelProviders.of(this).get(ServiceManagentViewModel::class.java)
        ProgressDialogUtils.getInstance().showProgress(this,true)
        serviceViewModel.getServiceType(
        access_token = SharePrefUtils.getSharedInstance(this@ServiceManagementActivity).accessToken!!
    )

        val rg_freePaid =findViewById<View>(R.id.rg_freePaid) as RadioGroup
        rg_freePaid.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {

                R.id.rb_free -> {
                    delivery_charges = "0" // 0 for free
                    cl_distance.visibility = View.GONE
                    cl_paid.visibility = View.GONE
                }

                R.id.rb_paid -> {
                    delivery_charges = "1" // 1 for paid
                    cl_paid.visibility = View.VISIBLE
                    cl_distance.visibility = View.GONE
                }

                R.id.rb_distance -> {
                    delivery_charges = "2" // 1 for paid
                    cl_distance.visibility = View.VISIBLE
                    cl_paid.visibility = View.GONE
                }
            }
        }
    }

    override fun initControl() {

        iv_service_back.setOnClickListener(this)
        btn_continue.setOnClickListener(this)
        btn_edit.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_service_back -> {

                onBackPressed()
            }
            R.id.btn_continue -> {

                if (isValidInputs()) {

                    ProgressDialogUtils.getInstance().showProgress(this, true)
                    serviceViewModel.setDeliveryCharge(
                        access_token = SharePrefUtils.getSharedInstance(this@ServiceManagementActivity).accessToken!!,
                        type = delivery_charges,
                        normal_delivery_charge = et_normalDelCharges.text.toString(),
                        express_delivery_charge = et_expressDelCharges.text.toString(),
                        days_for_normal = et_numberDays.text.toString(),
                        days_for_express =  et_normalDelCharges.text.toString(),
                        zero_to_four = et_zeroFourCharges.text.toString(),
                        four_to_eight = et_fourEightCharges.text.toString(),
                        eight_to_twelve = et_eightTwelveCharges.text.toString(),
                        minimum_order_charges = et_minimum_order.text.toString(),
                        maximum_order_charges = et_maximum_order.text.toString()
                    )

                }
            }

            R.id.btn_edit -> {

                startActivity(
                    Intent(this, EditServiceMgmtActivity::class.java).apply {
                        //  putExtra("product_id",arrayList[position].brand_id)
                        // putExtra("cat_id",cat_id)
                    })
            }


        }

    }

    private fun isValidInputs(): Boolean {

        if (et_normalDelCharges.getString().isEmpty()) {
            et_normalDelCharges.requestFocus()
            Toast.makeText(this,R.string.please_enter_value, Toast.LENGTH_SHORT).show()
            return false
        }

        else if(et_numberDays.getString().isEmpty()) {
            et_numberDays.requestFocus()
            Toast.makeText(this, R.string.please_enter_value, Toast.LENGTH_SHORT).show()
            return false
        }

        else if(et_expressDelCharges.getString().isEmpty()) {
            et_expressDelCharges.requestFocus()
            Toast.makeText(this, R.string.please_enter_value, Toast.LENGTH_SHORT).show()
            return false
        }

        else if(et_minimum_order.getString().isEmpty()) {
            et_minimum_order.requestFocus()
            Toast.makeText(this, R.string.please_enter_value, Toast.LENGTH_SHORT).show()
            return false
        }

        else if(et_maximum_order.getString().isEmpty()) {
            et_maximum_order.requestFocus()
            Toast.makeText(this, R.string.please_enter_value, Toast.LENGTH_SHORT).show()
            return false
        }

        if (!NetworkUtils.isInternetAvailable(applicationContext!!)) {
            Toast.makeText(this,R.string.error_internet, Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    fun loadServiceList(
        arrayList: List<GetDeliveryResponse.Response.Servicetype>
    )
    {


        rv_service_list.layoutManager= LinearLayoutManager(this@ServiceManagementActivity, LinearLayoutManager.VERTICAL,false)
        rv_service_list.adapter= ServiceTypeAdapter(this@ServiceManagementActivity,arrayList)

    }
    private fun myObserver() {

        serviceViewModel.responsegetDelivery.observe(this@ServiceManagementActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            Log.d("servicetag", it.toString())
            Log.d("servicetag1", it.response!!.servicetype.toString())
            loadServiceList((it.response!!.servicetype!!))

        })

        serviceViewModel.responseSetDelivery.observe(this@ServiceManagementActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            startActivity(
                Intent(this, MainActivity::class.java).apply {
                    //  putExtra("product_id",arrayList[position].brand_id)
                    // putExtra("cat_id",cat_id)
                })

        })

        serviceViewModel.error.observe(this@ServiceManagementActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            ErrorUtil.handlerGeneralError(this@ServiceManagementActivity, it)

        })

    }
}