package com.app.serviceprivider.adapters

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.app.serviceprivider.R
import com.app.serviceprivider.activity.ui.service_mgmt.ServiceDetailActivity
import com.app.serviceprivider.model.GetDeliveryResponse

import kotlinx.android.synthetic.main.service_mgmt_list.view.*


class ServiceTypeAdapter(
    var context: Context,
    var arrayList: List<GetDeliveryResponse.Response.Servicetype>

) :
    RecyclerView.Adapter<ServiceTypeAdapter.ViewHolder>() {

   // var created_on:String = ""
//   var dateString: String = ""
  //  var listActiveOrder: ArrayList<ServiceTypeResponse.Response.OrderObj.FindData.VarientDetails>? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceTypeAdapter.ViewHolder =
        ServiceTypeAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.service_mgmt_list, parent, false)
        )

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemView.tv_serviceTitle.text = arrayList[position].name
        Glide.with(context)
            .load("http://15.207.1.62:3536/"+arrayList[position].image)
            .placeholder(R.drawable.home_active)

            .into(holder.itemView.iv_service);

        holder.itemView.setOnClickListener {

            context.startActivity(Intent(context,ServiceDetailActivity::class.java)
                .putExtra("service_id",arrayList[position]._id)
                .putExtra("service_name",arrayList[position].name)
                .putExtra("service_image",arrayList[position].image)
            )
        }


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }


}