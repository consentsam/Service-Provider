package com.app.serviceprivider.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.serviceprivider.R
import com.app.serviceprivider.model.PickUpTimeModel
import kotlinx.android.synthetic.main.pick_up_date_list.view.*

class PickUpTimeAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var pickTimeList: List<PickUpTimeModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return OfflineDashViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pick_up_date_list, parent, false)
        )


    }

    override fun getItemCount(): Int {
        return pickTimeList.size

    }

    fun submitList(blogList: List<PickUpTimeModel>){
        pickTimeList = blogList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is OfflineDashViewHolder -> {
                holder.bind(pickTimeList.get(position))

            }

        }

    }

    class OfflineDashViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

       val tv_pick_month = itemView.tv_pick_month
        val tv_pick_day = itemView.tv_pick_day
     //   val blog_author = itemView.blog_author

        fun bind(blogPost: PickUpTimeModel){




            tv_pick_month.setText(blogPost.title_month)
            tv_pick_day.setText(blogPost.title_day)

        }

    }
}