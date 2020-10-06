package com.app.serviceprivider.data

import com.app.serviceprivider.model.OfflineDashboardModel


class OfflineStaticData{

    companion object{

        fun createDataSet(): ArrayList<OfflineDashboardModel>{
            val list = ArrayList<OfflineDashboardModel>()
            list.add(
                OfflineDashboardModel(
                    "Ongoing Orders",
                    "",
                    "",
                    ""
                )
            )
            /*list.add(
                OfflineDashboardModel(
                    "Cancelled Orders",
                    "",
                    "",
                    ""
                )
            )

            list.add(
                OfflineDashboardModel(
                    "Completed Orders",
                    "",
                    "",
                    ""
                )
            )
            list.add(
                OfflineDashboardModel(
                    "Most Booked Services",
                    "",
                    "",
                    ""
                )
            )
            list.add(
                OfflineDashboardModel(
                    "Total Revenue Till Now",
                    "",
                    "",
                    ""
                )
            )
            list.add(
                OfflineDashboardModel(
                    "Total Rating Given",
                    "",
                    "",
                    ""
                )
            )*/

            return list
        }
    }
}