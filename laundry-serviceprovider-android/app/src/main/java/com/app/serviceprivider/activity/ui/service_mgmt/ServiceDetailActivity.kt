package com.app.serviceprivider.activity.ui.service_mgmt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.app.serviceprivider.R
import com.app.serviceprivider.adapters.ServiceItemAdapter
import com.app.serviceprivider.listner.MyEventListner
import com.app.serviceprivider.model.ServiceItemResponse
import com.app.serviceprivider.utils.ErrorUtil
import com.app.serviceprivider.utils.SharePrefUtils
import com.app.serviceprivider.utils.showToast
import com.singlevendor.base.NewBaseActivity
import com.singlevendor.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.activity_service_detail.*

class ServiceDetailActivity : NewBaseActivity(), View.OnClickListener, MyEventListner {

    lateinit var serviceItemAdapter: ServiceItemAdapter
    lateinit var serviceItemViewModel: ServiceDetailViewModel


    var service_type: String = ""
    var service_id: String = ""
    var service_image:String =""
    var category: String = ""
    var item_id: String = ""
    var _id: String = ""
    var position: Int? = null
    var dataAddItemList = ArrayList<ServiceItemResponse.Response>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_detail)

        service_type = intent.getStringExtra("service_name")
        service_id = intent.getStringExtra("service_id")
        service_image = intent.getStringExtra("service_image")
        tv_servicename.text = service_type
        //iv_service
        Glide.with(this)
            .load("http://15.207.1.62:3536/"+service_image)
            .placeholder(R.drawable.home_active)

            .into(iv_service);
        init()
        initControl()
        initAdapter()
        myObserver()
        if (NetworkUtils.isInternetAvailable(this)) {
            hitAddProductListing()
        } else {
            showToast(resources.getString(R.string.error_internet), applicationContext)
        }
    }

    private fun initAdapter() {
        serviceItemAdapter = ServiceItemAdapter(this, dataAddItemList, this)
        rv_service_item.adapter = serviceItemAdapter
    }

    override fun init() {


        // Log.d("service",service_id)
        serviceItemViewModel = ViewModelProvider(this).get(ServiceDetailViewModel::class.java)

        //serviceItemViewModel.getServiceItem(access_token = "access5jfe83wwkdscslzm")"5f363ba34a9df962aa80a5f6"


    }

    private fun hitAddProductListing() {


        ProgressDialogUtils.getInstance().showProgress(this, true)
        serviceItemViewModel.getServiceItem(
            access_token = SharePrefUtils.getSharedInstance(this@ServiceDetailActivity).accessToken!!,
            sevice_type = service_id
        )
    }


    override fun initControl() {

        iv_serviceDetail_back.setOnClickListener(this)
        btn_add_item.setOnClickListener(this)
    }


    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.iv_serviceDetail_back -> {

                onBackPressed()
            }

            R.id.btn_add_item -> {

                startActivityForResult(
                    Intent(this, AddItemActivity::class.java).apply {
                        putExtra("service_type", service_id)
                        putExtra("category", category)
                        putExtra("item_id", item_id)
                    }, 1002
                )
            }
        }
    }

    fun loadServiceItemList(
        arrayList: List<ServiceItemResponse.Response>
    ) {
        /* rv_service_item.addOnItemTouchListener(RecyclerItemClickListenr(this, rv_service_item, object : RecyclerItemClickListenr.OnItemClickListener {

             override fun onItemClick(view: View, position: Int) {

                 service_type = arrayList.get(position).service_type.toString()
                 category = arrayList.get(position).category.toString()
                 item_id = arrayList.get(position).item_id.toString()

             }
             override fun onItemLongClick(view: View?, position: Int) {


             }
         }))*/

        /* rv_service_item.layoutManager =
             LinearLayoutManager(this@ServiceDetailActivity, LinearLayoutManager.VERTICAL, false)
         rv_service_item.adapter = ServiceItemAdapter(this@ServiceDetailActivity, arrayList, this)
 */
        /* rv_service_item.addOnItemTouchListener(RecyclerItemClickListenr(this, rv_service_item, object : RecyclerItemClickListenr.OnItemClickListener {

             override fun onItemClick(view: View, position: Int) {

                 service_type = arrayList.get(position).service_type.toString()
                // Toast.makeText(this@ServiceDetailActivity,service_type,Toast.LENGTH_SHORT).show()
                 category = arrayList.get(position).category.toString()
                 item_id = arrayList.get(position).item_id.toString()

             }
             override fun onItemLongClick(view: View?, position: Int) {

                 service_type = arrayList.get(position).service_type.toString()

             }
         }))*/

    }

    private fun myObserver() {

        serviceItemViewModel.responseServiceItem.observe(this@ServiceDetailActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            //loadServiceItemList(it.response!!)
            dataAddItemList.clear()
            dataAddItemList.addAll(it.response!!)
            serviceItemAdapter.notifyDataSetChanged()


            /* if (it.response!= null){

                 val serviceItemList:ArrayList<String> = ArrayList()

                 for (item in it.response!!){

                    // serviceItemList.add(item.service_type!!)

                     service_type = serviceItemList.add(item.service_type!!).toString()
                     category = serviceItemList.add(item.category!!).toString()
                     item_id = serviceItemList.add(item.item_id!!).toString()
                     _id =serviceItemList.add(item._id!!).toString()
                 }
             }*/

            /* service_type = it.response!![0].service_type!!
             category = it.response!![0].category!!
             item_id = it.response!![0].item_id!!
             _id = it.response!![0]._id!!*/


        })

        serviceItemViewModel.responseDeleteItem.observe(this@ServiceDetailActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()


            serviceItemViewModel.getServiceItem(
                access_token = SharePrefUtils.getSharedInstance(this@ServiceDetailActivity).accessToken!!,
                sevice_type = service_type
            )

        })

        serviceItemViewModel.responseChnageStatus.observe(this@ServiceDetailActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

            // serviceItemViewModel.getServiceItem(access_token = SharePrefUtils.getSharedInstance(this@ServiceDetailActivity).accessToken!!)

        })

        serviceItemViewModel.error.observe(this@ServiceDetailActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            ErrorUtil.handlerGeneralError(this@ServiceDetailActivity, it)

        })

    }

    override fun OnDeleteListner(pos: Int, service_id: String) {
        dataAddItemList.removeAt(pos)
        serviceItemAdapter.notifyDataSetChanged()
        serviceItemViewModel.getDeleteItem(
            serviceId = service_id

        )

    }

    override fun OnEditItemListner(
        pos: Int, service_type: String, category: String, item_id: String, id: String
    ) {
        val intent = Intent(this@ServiceDetailActivity, EditAddItemActivity::class.java)
        intent.putExtra("service_type_id", service_type)
        intent.putExtra("category_id", category)
        intent.putExtra("item_ids", item_id)
        intent.putExtra("ids", id)
        startActivity(intent)

    }

    override fun OnChangeStatus(
        pos: Int,
        service_id: String,
        change_status: String,
        service_type: String,
        category: String,
        item_id: String
    ) {

        serviceItemViewModel.getServiceStatus(

            serviceId = service_id,
            is_service_active = change_status


        )


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1002 -> {
                if (resultCode == Activity.RESULT_OK) {
                    hitAddProductListing()
                }
            }


        }


    }


}
