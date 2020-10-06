package com.app.serviceprivider.data

import com.app.serviceprivider.model.TimeSlotModel


class SlotTimeStaticData{

    companion object{
/*         val cal: Calendar = Calendar.getInstance()
           // cal.setTime(yourDate)
            cal.add(Calendar.DATE, 1)
            val sdf = SimpleDateFormat("dd MMMM") //Date and time
            val sdf_ = SimpleDateFormat("EEEE")
            val cal1: Calendar = Calendar.getInstance()
            // cal.setTime(yourDate)
            cal1.add(Calendar.DATE, 2)


            val cal2: Calendar = Calendar.getInstance()
            // cal.setTime(yourDate)
            cal2.add(Calendar.DATE, 3)
            val cal3: Calendar = Calendar.getInstance()
            // cal.setTime(yourDate)
            cal3.add(Calendar.DATE, 4)
            val cal4: Calendar = Calendar.getInstance()
            // cal.setTime(yourDate)
            cal4.add(Calendar.DATE, 5)

            val cal5: Calendar = Calendar.getInstance()
            // cal.setTime(yourDate)
            cal5.add(Calendar.DATE, 6)
            val cal6: Calendar = Calendar.getInstance()
            // cal.setTime(yourDate)
            cal6.add(Calendar.DATE, 7)*/
        fun createDataSet(): ArrayList<TimeSlotModel>{
            val list = ArrayList<TimeSlotModel>()
            list.add(
                TimeSlotModel(

                    "12 August",
                    "Monday",
                    ""
                )
            )
            list.add(
                TimeSlotModel(
                        "13 August",
                        "Tuesday",
                        ""
                    )
                    )

            list.add(
                TimeSlotModel(
                    "14 August",
                    "Wednesday",
                    ""
                )
            )
            list.add(
                TimeSlotModel(
                    "15 August",
                    "Thrusday",
                    ""
                )
            )
            /*list.add(
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