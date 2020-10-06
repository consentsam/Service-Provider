package com.app.serviceprivider.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.app.serviceprivider.R
import com.app.serviceprivider.model.OnlineDashboardModel
import kotlinx.android.synthetic.main.online_dashboard_list.view.*

class OnlineDashboardAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onlineDashboardList: List<OnlineDashboardModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return OnlineDashViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.online_dashboard_list, parent, false)
        )


    }

    override fun getItemCount(): Int {
        return onlineDashboardList.size

    }

    fun submitList(blogList: List<OnlineDashboardModel>){
        onlineDashboardList = blogList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is OnlineDashViewHolder -> {
                holder.bind(onlineDashboardList.get(position))
            }

        }
    }

    class OnlineDashViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

       val blog_image = itemView.iv_onlineDashboard
        val tv_order_type = itemView.tv_order_type
       val tv_number = itemView.tv_number

        fun bind(blogPost: OnlineDashboardModel){

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(blogPost.image)
                .into(blog_image)
            tv_order_type.setText(blogPost.title)
            tv_number.setText(blogPost.number)

        }

    }
}