package com.app.serviceprivider.activity.ui.coll_delivery_mgmt

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CalendarView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.serviceprivider.R
import com.app.serviceprivider.activity.ui.slot_collection.SlotCollectionActivity
import com.app.serviceprivider.adapters.GetTimeSlotAdapter
import com.app.serviceprivider.bean.TimeSlotResponce
import com.app.serviceprivider.utils.ErrorUtil
import com.app.serviceprivider.utils.SharePrefUtils
import com.singlevendor.base.NewBaseActivity
import com.singlevendor.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.activity_coll_del_mgmt.*
import java.util.*


class CollDelMgmtActivity : NewBaseActivity(),View.OnClickListener,
    CalendarView.OnDateChangeListener {
    var cat_id:String = ""
    var dayOfWeek:String=""
    var month:String=""
    lateinit var collDelMgmtViewModel: CollDelMgmtViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coll_del_mgmt)

        init()
        initControl()
        myObserver()
    }

    override fun onResume() {
        super.onResume()
            collDelMgmtViewModel.getTimeslot(
                access_token = SharePrefUtils.getSharedInstance(this).accessToken!!,
                slot_type = "1",
                day = dayOfWeek
            )
                    collDelMgmtViewModel.getTimeslot(
                    access_token = SharePrefUtils.getSharedInstance(this).accessToken!!,
            slot_type = "2",
            day = dayOfWeek
        )
    }

    private fun myObserver() {
        collDelMgmtViewModel.responceTimeSlotResponce.observe(this@CollDelMgmtActivity, androidx.lifecycle.Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            Log.d("servicetag", it.toString())
            if(it.response!!.size>0) {
                if (it.response!!.get(0).slot_type=="2") {
                    loadServiceList((it.response!!.get(0).time_slots!!))
                    Log.d("timeslot",it.response!!.get(0).time_slots!!.toString())
                }
                if (it.response!!.get(0).slot_type=="1") {
                    loadServiceList1((it.response!!.get(0).time_slots!!))
                }
            }

        })



        collDelMgmtViewModel.error.observe(this@CollDelMgmtActivity, androidx.lifecycle.Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            ErrorUtil.handlerGeneralError(this@CollDelMgmtActivity, it)

        })

    }

    private fun loadServiceList( arrayList: List<TimeSlotResponce.Response.TimeSlots>) {
        Log.d("timeslot1",arrayList.toString())
        rv_time_slot_delivery.layoutManager= LinearLayoutManager(this@CollDelMgmtActivity, LinearLayoutManager.VERTICAL,false)
        rv_time_slot_delivery.adapter= GetTimeSlotAdapter(this@CollDelMgmtActivity,arrayList)
    }
    private fun loadServiceList1( arrayList: List<TimeSlotResponce.Response.TimeSlots>) {
        Log.d("timeslot2",arrayList.toString())
        rv_timeCollection.layoutManager= LinearLayoutManager(this@CollDelMgmtActivity, LinearLayoutManager.VERTICAL,false)
        rv_timeCollection.adapter= GetTimeSlotAdapter(this@CollDelMgmtActivity,arrayList)
    }

    override fun init() {

        collDelMgmtViewModel = ViewModelProviders.of(this).get(CollDelMgmtViewModel::class.java)
        val calendar = Calendar.getInstance()
        if ( calendar[Calendar.MONTH].toString()=="0"){
            month="January"
        }
        if ( calendar[Calendar.MONTH].toString()=="1"){
            month="February"
        }
        if ( calendar[Calendar.MONTH].toString()=="2"){
            month="March"
        }
        if ( calendar[Calendar.MONTH].toString()=="3"){
            month="April"
        }
        if ( calendar[Calendar.MONTH].toString()=="4"){
            month="May"
        }
        if ( calendar[Calendar.MONTH].toString()=="5"){
            month="June"
        }
        if ( calendar[Calendar.MONTH].toString()=="6"){
            month="July"
        }
        if ( calendar[Calendar.MONTH].toString()=="7"){
            month="August"
        }
        if ( calendar[Calendar.MONTH].toString()=="8"){
            month="September"
        }
        if ( calendar[Calendar.MONTH].toString()=="9"){
            month="October"
        }
        if ( calendar[Calendar.MONTH].toString()=="10"){
            month="November"
        }
        if ( calendar[Calendar.MONTH].toString()=="11"){
            month="December"
        }




        tv_timeCollection.setText("Time slot for collection for "+ calendar[Calendar.DAY_OF_MONTH].toString()+" "+ month)
        tv_timeDelivery.setText("Time slot for delivery for "+ calendar[Calendar.DAY_OF_MONTH].toString()+" "+ month)
       dayOfWeek = calendar[Calendar.DAY_OF_WEEK].toString()
        Log.d("dayofweek1",dayOfWeek)
        ProgressDialogUtils.getInstance().showProgress(this, true)


    }

    override fun initControl() {

        iv_coll_del_back.setOnClickListener(this)
        btn_time_collection.setOnClickListener(this)
        btn_time_delivery.setOnClickListener(this)
        btn_yourself_unavailable.setOnClickListener(this)
        calenderView.setOnDateChangeListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_coll_del_back -> {

           onBackPressed()
            }

            R.id.btn_time_collection -> {
                cat_id="1"
                startActivity(
                    Intent(this, SlotCollectionActivity::class.java).apply {
                      //putExtra("product_id",arrayList[position].brand_id)
                       putExtra("category",cat_id)
                    })
            }
            R.id.btn_time_delivery -> {
                cat_id="2"
                startActivity(
                    Intent(this, SlotCollectionActivity::class.java).apply {
                        //putExtra("product_id",arrayList[position].brand_id)
                        putExtra("category",cat_id)
                    })
            }
            R.id.btn_yourself_unavailable -> {
                cat_id="3"
                startActivity(
                    Intent(this, SlotCollectionActivity::class.java).apply {
                        //putExtra("product_id",arrayList[position].brand_id)
                        putExtra("category",cat_id)
                    })
            }

        }


    }

    override fun onSelectedDayChange(view: CalendarView, year: Int, month1: Int, dayOfMonth: Int) {
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(year, month1, dayOfMonth)
         dayOfWeek= calendar.get(Calendar.DAY_OF_WEEK).toString()
       val month_= calendar.get(Calendar.MONTH).toString()
        Log.d("dayofweek",dayOfWeek)
        if ( month_=="0"){
            month="January"
        }
        if ( month_=="1"){
            month="February"
        }
        if ( month_=="2"){
            month="March"
        }
        if ( month_=="3"){
            month="April"
        }
        if (month_=="4"){
            month="May"
        }
        if ( month_=="5"){
            month="June"
        }
        if ( month_=="6"){
            month="July"
        }
        if ( month_=="7"){
            month="August"
        }
        if ( month_=="8"){
            month="September"
        }
        if ( month_=="9"){
            month="October"
        }
        if (month_=="10"){
            month="November"
        }
        if ( month_=="11"){
            month="December"
        }

        tv_timeCollection.setText("Time slot for collection for "+ calendar.get(Calendar.DAY_OF_MONTH).toString()+" "+month)
        tv_timeDelivery.setText("Time slot for delivery for "+ calendar.get(Calendar.DAY_OF_MONTH).toString()+ " "+month)
        collDelMgmtViewModel.getTimeslot(
            access_token = SharePrefUtils.getSharedInstance(this).accessToken!!,
            slot_type = "1",
            day = dayOfWeek
        )
      collDelMgmtViewModel.getTimeslot(
            access_token = SharePrefUtils.getSharedInstance(this).accessToken!!,
            slot_type ="1" ,
            day = dayOfWeek
        )
    }
}