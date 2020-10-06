package com.app.serviceprivider.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.app.serviceprivider.R
import com.app.serviceprivider.model.OfflineDashboardModel
import kotlinx.android.synthetic.main.offline_item_list.view.*

class OfflineDashboardAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var offlineDashboardList: List<OfflineDashboardModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return OfflineDashViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.offline_item_list, parent, false)
        )


    }

    override fun getItemCount(): Int {
        return offlineDashboardList.size

    }

    fun submitList(blogList: List<OfflineDashboardModel>){
        offlineDashboardList = blogList
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is OfflineDashViewHolder -> {
                holder.bind(offlineDashboardList.get(position))
            }

        }
        holder.itemView.tv_order_request.setOnClickListener {



        }
    }

    class OfflineDashViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){

       val iv_order = itemView.iv_order
        val tv_order_request = itemView.tv_order_request
     //   val blog_author = itemView.blog_author

        fun bind(blogPost: OfflineDashboardModel){

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

         /*   Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(blogPost.image)
                .into(iv_order)*/
            tv_order_request.setText(blogPost.title)
            //blog_author.setText(blogPost.username)

        }

    }
}