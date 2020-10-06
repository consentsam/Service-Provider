package com.app.serviceprivider.adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.serviceprivider.R
import com.app.serviceprivider.listner.RecyclerItemClickListenr
import com.app.serviceprivider.model.GetOrderResponse


import kotlinx.android.synthetic.main.order_card.view.*




class OrderRequestAdapter(
    var context: Context,
    var arrayList: List<GetOrderResponse.Response>,
    var listner: RecyclerItemClickListenr.OnItemClickListener
) :
    RecyclerView.Adapter<OrderRequestAdapter.ViewHolder>() {




    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderRequestAdapter.ViewHolder =
        OrderRequestAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.order_card, parent, false)
        )

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemView.order_id_tv.text = arrayList[position]._id

        holder.itemView.accept_btn.setOnClickListener {
            listner.onItemClick(holder.itemView.accept_btn,position)
        }
        holder.itemView.reject_btn.setOnClickListener {
            listner.onItemClick(holder.itemView.reject_btn,position)
        }
        /*   holder.itemView.accept_btn.setOnClickListener {
               openDialog("accept")

           }
           holder.itemView.reject_btn.setOnClickListener {
               openDialog("reject")

           }*/


    }

    /*private fun openDialog(s: String) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.reject_dialog)

        if (s=="accept")
        {
            dialog.tv_forgot_password.text="Are You sure You want to Accept this order"
            dialog.register_dialog_ok_iv.setBackgroundResource(R.drawable.popup_check_icon)
        }
        if (s=="reject"){
            dialog.tv_forgot_password.text="Are You sure You want to Reject this order"
            dialog.register_dialog_ok_iv.setBackgroundResource(R.drawable.order_reject)
        }

        val reject_yes_btn: Button = dialog.findViewById(R.id.reject_yes_btn) as Button
        val reject_no_btn: Button = dialog.findViewById(R.id.reject_no_btn) as Button
        reject_yes_btn.setOnClickListener(View.OnClickListener {
            openConfimation("reject")
            dialog.dismiss()
        })
        reject_no_btn.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
        })

        dialog.show()

    }

    private fun openConfimation(s: String) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.confirmationdialog)

        if (s=="accept"){
            dialog.iv_confimatin.setBackgroundResource(R.drawable.popup_check_icon)
            dialog.tv_confirmation.setText("Order Confirm")
        }
        if (s=="reject"){
            dialog.iv_confimatin.setBackgroundResource(R.drawable.order_reject)
            dialog.tv_confirmation.setText("Order Cancelled")
        }



        dialog.show()
    }*/

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }


}