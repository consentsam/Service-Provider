package com.app.serviceprivider.activity.ui.order_request_management


import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.serviceprivider.R
import com.app.serviceprivider.adapters.OrderRequestAdapter
import com.app.serviceprivider.listner.RecyclerItemClickListenr
import com.app.serviceprivider.model.GetOrderResponse
import com.app.serviceprivider.utils.ErrorUtil
import com.app.serviceprivider.utils.SharePrefUtils
import com.singlevendor.base.NewBaseActivity
import com.singlevendor.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.accept_dialog.register_dialog_ok_iv
import kotlinx.android.synthetic.main.activity_order_management.*
import kotlinx.android.synthetic.main.confirmationdialog.*
import kotlinx.android.synthetic.main.reject_dialog.*


class OrderManagementActivity : NewBaseActivity(), View.OnClickListener ,RecyclerItemClickListenr.OnItemClickListener{

    lateinit var orderManagementViewModel: OrderManagementViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_management)
        init()
        initControl()
        myObserver()

    }

    override fun onResume() {
        super.onResume()
        orderManagementViewModel.getOrder(
            access_token = SharePrefUtils.getSharedInstance(this).accessToken!!,//"access5jfe8t8kesk239x",
            type = "1"
        )
    }

    private fun myObserver() {
        orderManagementViewModel.getOrderResponse.observe(this@OrderManagementActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            Log.d("servicetag", it.toString())

            loadServiceList((it.response!!))

        })
        orderManagementViewModel.error.observe(this@OrderManagementActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            ErrorUtil.handlerGeneralError(this@OrderManagementActivity, it)

        })
    }
    private fun loadServiceList(response: List<GetOrderResponse.Response>) {
        order_management_recyclerview.adapter= OrderRequestAdapter(this@OrderManagementActivity,response,this)

    }
    override fun init() {
        orderManagementViewModel = ViewModelProviders.of(this).get(OrderManagementViewModel::class.java)

    }

    override fun initControl() {
        iv_order_management_back.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_order_management_back -> {

                onBackPressed()
            }

        }
        }

    override fun onItemClick(view: View, position: Int) {
        when(view?.id) {
            R.id.accept_btn -> {
                openDialog("accept")


                // Toast.makeText(this,"click",Toast.LENGTH_SHORT).show()


            }
            R.id.reject_btn -> {
                openDialog("reject")


                // Toast.makeText(this,"click",Toast.LENGTH_SHORT).show()


            }
        }
    }

    private fun openDialog(s: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.reject_dialog)

        if (s=="accept")
        {
            dialog.tv_dialog.text="Are You sure You want to Accept this order"
            dialog.register_dialog_ok_iv.setBackgroundResource(R.drawable.popup_check_icon)
        }
        if (s=="reject"){
            dialog.tv_dialog.text="Are You sure You want to Reject this order"
            dialog.register_dialog_ok_iv.setBackgroundResource(R.drawable.order_reject)
        }

        val reject_yes_btn: Button = dialog.findViewById(R.id.reject_yes_btn) as Button
        val reject_no_btn: Button = dialog.findViewById(R.id.reject_no_btn) as Button
        reject_yes_btn.setOnClickListener(View.OnClickListener {
            openConfimation(dialog.tv_dialog.text.toString())
            dialog.dismiss()
        })
        reject_no_btn.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })

        dialog.show()

    }

    private fun openConfimation(s: String) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.confirmationdialog)

        if (s=="Are You sure You want to Accept this order"){
            dialog.iv_confimatin.setBackgroundResource(R.drawable.popup_check_icon)
            dialog.tv_confirmation.setText("Order Confirm")
        }
        if (s=="Are You sure You want to Reject this order"){
            dialog.iv_confimatin.setBackgroundResource(R.drawable.order_reject)
            dialog.tv_confirmation.setText("Order Cancelled")
        }



        dialog.show()
    }
    override fun onItemLongClick(view: View?, position: Int) {

    }


}