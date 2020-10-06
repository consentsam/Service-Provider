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
import kotlinx.android.synthetic.main.order_card.view.order_id_tv
import kotlinx.android.synthetic.main.order_card.view.reject_btn
import kotlinx.android.synthetic.main.task_card.view.*


class TaskRequestAdapter(
    var context: Context,
    var arrayList: List<GetOrderResponse.Response>

) :
    RecyclerView.Adapter<TaskRequestAdapter.ViewHolder>() {




    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskRequestAdapter.ViewHolder =
        TaskRequestAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_card, parent, false)
        )

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemView.order_id_tv.text = arrayList[position]._id

        holder.itemView.pickup_tv.text=arrayList[position].pickup_date+ " | "+ arrayList[position].pickup_time_slots!!.start_time+"-"+ arrayList[position].pickup_time_slots!!.end_time

        holder.itemView.delivery_tv.text=arrayList[position].delivery_date+ " | "+ arrayList[position]._delivery_time_slots!!.start_time+"-"+ arrayList[position]._delivery_time_slots !!.end_time
        holder.itemView.user_name_tv.text=arrayList[position].user_id

    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }


}