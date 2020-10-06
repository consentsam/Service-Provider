package com.app.serviceprivider.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.app.serviceprivider.R
import com.app.serviceprivider.model.TimeSlotModel
import kotlinx.android.synthetic.main.pick_up_date_list.view.*
import kotlinx.android.synthetic.main.time_slot_list.view.*

class TimeSlotAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var pickTimeList: List<TimeSlotModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return OfflineDashViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.time_slot_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return pickTimeList.size

    }

    fun submitList(blogList: List<TimeSlotModel>){
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

       val rb_slot_time = itemView.rb_slot_time
        val tv_pick_day = itemView.tv_pick_day
     //   val blog_author = itemView.blog_author

        fun bind(blogPost: TimeSlotModel){

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

         /*   Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(blogPost.image)
                .into(iv_order)*/
            rb_slot_time.setText(blogPost.time_slot)
          //  tv_pick_day.setText(blogPost.title_day)

        }

    }
}