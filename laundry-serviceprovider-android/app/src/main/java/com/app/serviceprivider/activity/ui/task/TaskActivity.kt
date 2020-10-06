package com.app.serviceprivider.activity.ui.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.serviceprivider.R
import com.app.serviceprivider.activity.ui.order_request_management.OrderManagementViewModel
import com.app.serviceprivider.adapters.OrderRequestAdapter
import com.app.serviceprivider.adapters.TaskRequestAdapter
import com.app.serviceprivider.model.GetOrderResponse
import com.app.serviceprivider.utils.ErrorUtil
import com.app.serviceprivider.utils.SharePrefUtils
import com.google.android.material.tabs.TabLayout
import com.singlevendor.base.NewBaseActivity
import com.singlevendor.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.activity_order_management.*
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity :  NewBaseActivity(), View.OnClickListener, TabLayout.OnTabSelectedListener {

    var type :String=""
    lateinit var taskViewModel: TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        init()
        initControl()
        myObserver()

    }
    private fun myObserver() {
        taskViewModel.getOrderResponse.observe(this@TaskActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            Log.d("servicetag", it.toString())

            loadServiceList((it.response!!))

        })
        taskViewModel.error.observe(this@TaskActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            ErrorUtil.handlerGeneralError(this@TaskActivity, it)

        })
    }
    private fun loadServiceList(response: List<GetOrderResponse.Response>) {
        task_recyclerview.adapter= TaskRequestAdapter(this@TaskActivity,response)

    }
    override fun init() {
        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Active Order"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Past Order"))

        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        taskViewModel.getOrder(
            access_token = SharePrefUtils.getSharedInstance(this).accessToken!!,//"access5jfe8t8kesk239x",
            type = "1"
        )

    }

    override fun initControl() {
     tabLayout.addOnTabSelectedListener(this)
    }
    override fun onClick(v: View?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
     if (tab!!.position==1){
      type="1"
         taskViewModel.getOrder(
             access_token = SharePrefUtils.getSharedInstance(this).accessToken!!,//"access5jfe8t8kesk239x",
             type = type
         )

     }
        if (tab!!.position==1){
            type="2"
            taskViewModel.getOrder(
                access_token = SharePrefUtils.getSharedInstance(this).accessToken!!,//"access5jfe8t8kesk239x",
                type = type
            )

        }
        else{
         type="1"
     }
    }
}