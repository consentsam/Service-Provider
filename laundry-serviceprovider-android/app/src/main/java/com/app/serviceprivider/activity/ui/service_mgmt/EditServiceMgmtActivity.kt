package com.app.serviceprivider.activity.ui.service_mgmt

import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import com.app.serviceprivider.R
import com.singlevendor.base.NewBaseActivity
import kotlinx.android.synthetic.main.activity_edit_service_mgmt.*
import kotlinx.android.synthetic.main.activity_edit_service_mgmt.cl_distance
import kotlinx.android.synthetic.main.activity_edit_service_mgmt.cl_paid

class EditServiceMgmtActivity : NewBaseActivity(),View.OnClickListener {

    var delivery_charges: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_service_mgmt)

        init()
        initControl()
        myObserver()
    }


    override fun init() {

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
                    cl_distance.visibility = View.VISIBLE
                    cl_paid.visibility = View.GONE
                }
            }
        }

    }

    override fun initControl() {

        btn_save.setOnClickListener(this)
        iv_edit_service_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.iv_edit_service_back -> {

                onBackPressed()
            }

            R.id.btn_save -> {

               finish()
            }
        }

    }

    private fun myObserver() {

    }

}