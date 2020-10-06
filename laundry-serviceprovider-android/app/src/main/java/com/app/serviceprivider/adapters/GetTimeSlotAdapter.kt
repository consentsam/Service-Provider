package com.app.serviceprivider.adapters

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.serviceprivider.R
import com.app.serviceprivider.bean.TimeSlotResponce

import kotlinx.android.synthetic.main.time_slot_layout.view.*


class GetTimeSlotAdapter(
    var context: Context,
    var arrayList: List<TimeSlotResponce.Response.TimeSlots>

) :
    RecyclerView.Adapter<GetTimeSlotAdapter.ViewHolder>() {

   // var created_on:String = ""
//   var dateString: String = ""
  //  var listActiveOrder: ArrayList<ServiceTypeResponse.Response.OrderObj.FindData.VarientDetails>? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GetTimeSlotAdapter.ViewHolder =
        GetTimeSlotAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.time_slot_layout, parent, false)
        )

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemView.tv_time_slot_collection.text = arrayList[position].start_time+"-"+ arrayList[position].end_time



    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }


}