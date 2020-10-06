package com.app.serviceprivider.data

import com.app.serviceprivider.R
import com.app.serviceprivider.model.OnlineDashboardModel


class OnlineStaticData{

    companion object{

        fun createDataSet(): ArrayList<OnlineDashboardModel>{
            val list = ArrayList<OnlineDashboardModel>()
            list.add(
                OnlineDashboardModel(
                    "Ongoing Orders",
                    "425",
                    R.drawable.ongoing_order,
                    ""
                )
            )
            list.add(
                OnlineDashboardModel(
                    "Cancelled Orders",
                    "425",
                    R.drawable.cancelled_orders,
                    ""
                )
            )

            list.add(
                OnlineDashboardModel(
                    "Completed Orders",
                    "425",
                    R.drawable.completed_orders,
                    ""
                )
            )
            list.add(
                OnlineDashboardModel(
                    "Most Booked Services",
                    "425",
                    R.drawable.most_booked_services,
                    ""
                )
            )
            list.add(
                OnlineDashboardModel(
                    "Total Revenue Till Now",
                    "40K",
                    R.drawable.total_revenue,
                    ""
                )
            )
            list.add(
                OnlineDashboardModel(
                    "Total Rating Given",
                    "4.8",
                    R.drawable.rating,
                    ""
                )
            )

            return list
        }
    }
}