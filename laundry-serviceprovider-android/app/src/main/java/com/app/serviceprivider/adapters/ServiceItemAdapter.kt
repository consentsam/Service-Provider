package com.app.serviceprivider.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.serviceprivider.R
import com.app.serviceprivider.listner.MyEventListner
import com.app.serviceprivider.model.ServiceItemResponse
import kotlinx.android.synthetic.main.service_item_list.view.*

class ServiceItemAdapter(

    var context: Context,
    var arrayList: List<ServiceItemResponse.Response>,
    var listner: MyEventListner

) :
    RecyclerView.Adapter<ServiceItemAdapter.ViewHolder>() {

    var service_type:String = ""
    var category:String = ""
    var item_id:String = ""

   // var created_on:String = ""
//   var dateString: String = ""
  //  var listActiveOrder: ArrayList<ServiceTypeResponse.Response.OrderObj.FindData.VarientDetails>? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServiceItemAdapter.ViewHolder =
        ServiceItemAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.service_item_list, parent, false)
        )

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                val view =holder.itemView
                val model =arrayList[position]

        holder.itemView.tv_itemName.text = model.item_name
        holder.itemView.tv_itemCategory.text = model.category_name
       holder.itemView.tv_itemPrice.text = "SAR ${model.price}"

        holder.itemView.iv_itemOnOff.setOnClickListener {

            if (arrayList[position].is_service_active!!){
                holder.itemView.iv_itemOnOff.setActivated(!holder.itemView.iv_itemOnOff.isActivated())
                listner.OnChangeStatus(position, arrayList[position]._id.toString(),arrayList[position].is_service_active.toString(),arrayList[position].service_type.toString(),arrayList[position].category.toString(),arrayList[position].item_id.toString())


            }
            else if (!arrayList[position].is_service_active!!){
                holder.itemView.iv_itemOnOff.setActivated(!holder.itemView.iv_itemOnOff.isActivated())
                listner.OnChangeStatus(position, arrayList[position]._id.toString(),arrayList[position].is_service_active.toString(),arrayList[position].service_type.toString(),arrayList[position].category.toString(),arrayList[position].item_id.toString())



            }

        }

        holder.itemView.iv_itemDelete.setOnClickListener {

            listner.OnDeleteListner(position, arrayList[position]._id.toString())



        }

        holder.itemView.iv_itemEdit.setOnClickListener {

            listner.OnEditItemListner(position, arrayList[position].service_type.toString(),
                arrayList[position].category.toString(),arrayList[position].item_id.toString(),arrayList[position]._id.toString())

        }


    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }


}