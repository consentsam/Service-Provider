package com.app.serviceprivider.activity.ui.slot_collection

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.ViewModelProviders

import com.app.serviceprivider.R
import com.app.serviceprivider.adapters.PickUpTimeAdapter
import com.app.serviceprivider.bean.TimeslotModel
import com.app.serviceprivider.data.PickUpStaticData
import com.app.serviceprivider.utils.ErrorUtil
import com.app.serviceprivider.utils.SharePrefUtils
import com.app.serviceprivider.utils.showToast
import com.singlevendor.base.NewBaseActivity
import com.singlevendor.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.activity_slot_collection.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SlotCollectionActivity : NewBaseActivity(),View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {
    lateinit var slotCollectionViewModel: SlotCollectionViewModel
    private lateinit var pickTimedapter: PickUpTimeAdapter
    val list = ArrayList<TimeslotModel>()
  /// private lateinit var timeslotModel: TimeslotModel
var category:String=""
    var start_time:String=""
    var end_time:String=""
    var day:String=""
    var access_token:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slot_collection)
        val cal: Calendar = Calendar.getInstance()
        // cal.setTime(yourDate)
        cal.add(Calendar.DATE, 0)
        val sdf = SimpleDateFormat("dd MMMM") //Date and time
        val sdf_ = SimpleDateFormat("EEEE")
        val cal1: Calendar = Calendar.getInstance()
        // cal.setTime(yourDate)
        cal1.add(Calendar.DATE, 1)


        val cal2: Calendar = Calendar.getInstance()
        // cal.setTime(yourDate)
        cal2.add(Calendar.DATE, 2)
        val cal3: Calendar = Calendar.getInstance()
        // cal.setTime(yourDate)
        cal3.add(Calendar.DATE, 3)
        val cal4: Calendar = Calendar.getInstance()
        // cal.setTime(yourDate)
        cal4.add(Calendar.DATE, 4)

        val cal5: Calendar = Calendar.getInstance()
        // cal.setTime(yourDate)
        cal5.add(Calendar.DATE, 5)
        val cal6: Calendar = Calendar.getInstance()
        // cal.setTime(yourDate)
        cal6.add(Calendar.DATE, 6)
        tv_pick_month1.setText( sdf.format( cal.getTime()))
        tv_pick_day1.setText(  sdf_.format( cal.getTime()))
        tv_pick_month2.setText( sdf.format( cal1.getTime()))
        tv_pick_day2.setText(  sdf_.format( cal1.getTime()))
        tv_pick_month3.setText( sdf.format( cal2.getTime()))
        tv_pick_day3.setText(  sdf_.format( cal2.getTime()))
        tv_pick_month4.setText( sdf.format( cal3.getTime()))
        tv_pick_day4.setText(  sdf_.format( cal3.getTime()))
        tv_pick_month5.setText( sdf.format( cal4.getTime()))
        tv_pick_day5.setText(  sdf_.format( cal4.getTime()))
        tv_pick_month6.setText( sdf.format( cal5.getTime()))
        tv_pick_day6.setText(  sdf_.format( cal5.getTime()))
        tv_pick_month7.setText( sdf.format( cal6.getTime()))
        tv_pick_day7.setText(  sdf_.format( cal6.getTime()))
        init()
        initControl()
            myObserver()
    }


    override fun init() {
        slotCollectionViewModel = ViewModelProviders.of(this).get(SlotCollectionViewModel::class.java)
        category = intent.getStringExtra("category")
        if (category=="1")
        {
            tvToolbarTitle.setText("Set Time Slot for Collection")
        }
        if (category=="2")
        {
            tvToolbarTitle.setText("Set Time Slot for Delivery")

        }
        if (category=="3"){
            tvToolbarTitle.setText("Set Time for Make Yourself Available")
            btn_addTimeSlot.setText("Confirm Unavailability")
        }
     //   pickUpRecyclerView()
      //  timeSlotRecyclerView()
     //   addDataSet()
    }



    private fun addDataSet() {

        val pictUpData = PickUpStaticData.createDataSet()
      //  val timeSlotData = SlotTimeStaticData.createDataSet()

        pickTimedapter.submitList(pictUpData)
//        slotTimedapter.submitList(timeSlotData)

    }

   /* private fun pickUpRecyclerView() {
        rv_pick_time_list.apply {
           // layoutManager = GridLayoutManager(this@SlotCollectionActivity, 1)
       layoutManager = LinearLayoutManager(this@SlotCollectionActivity, LinearLayoutManager.HORIZONTAL ,false)

            pickTimedapter = PickUpTimeAdapter()
            adapter = pickTimedapter

        }
    }*/



    override fun initControl() {

        iv_slot_coll_back.setOnClickListener(this)
        btn_addTimeSlot.setOnClickListener(this)
        rb_nine_ten.setOnCheckedChangeListener(this)
        rb_ten_eleven.setOnCheckedChangeListener(this)
        rb_eleven_twelve.setOnCheckedChangeListener(this)
        rb_twelve_one.setOnCheckedChangeListener(this)
        rb_two_three.setOnCheckedChangeListener(this)
        rb_three_four.setOnCheckedChangeListener(this)
        pick_up_card1.setOnClickListener(this)
        pick_up_card2.setOnClickListener(this)
        pick_up_card3.setOnClickListener(this)
        pick_up_card4.setOnClickListener(this)
        pick_up_card5.setOnClickListener(this)
        pick_up_card6.setOnClickListener(this)
        pick_up_card7.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_slot_coll_back -> {

                onBackPressed()
            }

            R.id.btn_addTimeSlot -> {
Log.d("tag",list.size.toString())

                if (NetworkUtils.isInternetAvailable(this)) {
                    if (isValidInputs()) {
                       // access_token= SharePrefUtils.getSharedInstance(this).accessToken!!
                        slotCollectionViewModel.getAddTimeSlot(
                            access_token = SharePrefUtils.getSharedInstance(this).accessToken!!,
                            time_slots=list,
                            slot_type =category,
                            day = day
                        )
                       // senTimeSlot(access_token)
                    }

                }
            }
            R.id.pick_up_card1 -> {
                pick_up_card1.setCardBackgroundColor(Color.parseColor("#6200EE"))
                pick_up_card2.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card3.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card4.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card5.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card6.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card7.setCardBackgroundColor(Color.parseColor("#144789"))
                if (tv_pick_day1.text =="Sunday")
                {
                    day="1"
                }
                if (tv_pick_day1.text =="Monday")
                {
                    day="2"
                }
                if (tv_pick_day1.text =="Tuesday")
                {
                    day="3"
                }
                if (tv_pick_day1.text =="Wednesday")
                {
                    day="4"
                }
                if (tv_pick_day1.text =="Thursday")
                {
                    day="5"
                }
                if (tv_pick_day1.text =="Friday")
                {
                    day="6"
                }
                if (tv_pick_day1.text =="Saturday")
                {
                    day="7"
                }


            }
            R.id.pick_up_card2 -> {
                pick_up_card2.setCardBackgroundColor(Color.parseColor("#6200EE"))
                pick_up_card1.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card3.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card4.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card5.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card6.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card7.setCardBackgroundColor(Color.parseColor("#144789"))
                if (tv_pick_day2.text =="Sunday")
                {
                    day="1"
                }
                if (tv_pick_day2.text =="Monday")
                {
                    day="2"
                }
                if (tv_pick_day2.text =="Tuesday")
                {
                    day="3"
                }
                if (tv_pick_day2.text =="Wednesday")
                {
                    day="4"
                }
                if (tv_pick_day2.text =="Thursday")
                {
                    day="5"
                }
                if (tv_pick_day2.text =="Friday")
                {
                    day="6"
                }
                if (tv_pick_day2.text =="Saturday")
                {
                    day="7"
                }

            }
            R.id.pick_up_card3 -> {
                pick_up_card3.setCardBackgroundColor(Color.parseColor("#6200EE"))
                pick_up_card2.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card1.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card4.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card5.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card6.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card7.setCardBackgroundColor(Color.parseColor("#144789"))
                if (tv_pick_day3.text =="Sunday")
                {
                    day="1"
                }
                if (tv_pick_day3.text =="Monday")
                {
                    day="2"
                }
                if (tv_pick_day3.text =="Tuesday")
                {
                    day="3"
                }
                if (tv_pick_day3.text =="Wednesday")
                {
                    day="4"
                }
                if (tv_pick_day3.text =="Thursday")
                {
                    day="5"
                }
                if (tv_pick_day3.text =="Friday")
                {
                    day="6"
                }
                if (tv_pick_day3.text =="Saturday")
                {
                    day="7"
                }

            }
            R.id.pick_up_card4 -> {
                pick_up_card4.setCardBackgroundColor(Color.parseColor("#6200EE"))
                pick_up_card2.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card3.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card1.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card5.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card6.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card7.setCardBackgroundColor(Color.parseColor("#144789"))
                if (tv_pick_day4.text =="Sunday")
                {
                    day="1"
                }
                if (tv_pick_day4.text =="Monday")
                {
                    day="2"
                }
                if (tv_pick_day4.text =="Tuesday")
                {
                    day="3"
                }
                if (tv_pick_day4.text =="Wednesday")
                {
                    day="4"
                }
                if (tv_pick_day4.text =="Thursday")
                {
                    day="5"
                }
                if (tv_pick_day4.text =="Friday")
                {
                    day="6"
                }
                if (tv_pick_day4.text =="Saturday")
                {
                    day="7"
                }

            }
            R.id.pick_up_card5 -> {
                pick_up_card5.setCardBackgroundColor(Color.parseColor("#6200EE"))
                pick_up_card2.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card3.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card4.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card1.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card6.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card7.setCardBackgroundColor(Color.parseColor("#144789"))
                if (tv_pick_day5.text =="Sunday")
                {
                    day="1"
                }
                if (tv_pick_day5.text =="Monday")
                {
                    day="2"
                }
                if (tv_pick_day5.text =="Tuesday")
                {
                    day="3"
                }
                if (tv_pick_day5.text =="Wednesday")
                {
                    day="4"
                }
                if (tv_pick_day5.text =="Thursday")
                {
                    day="5"
                }
                if (tv_pick_day5.text =="Friday")
                {
                    day="6"
                }
                if (tv_pick_day5.text =="Saturday")
                {
                    day="7"
                }

            }
            R.id.pick_up_card6 -> {
                pick_up_card6.setCardBackgroundColor(Color.parseColor("#6200EE"))
                pick_up_card2.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card3.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card4.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card5.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card1.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card7.setCardBackgroundColor(Color.parseColor("#144789"))
                if (tv_pick_day6.text =="Sunday")
                {
                    day="1"
                }
                if (tv_pick_day6.text =="Monday")
                {
                    day="2"
                }
                if (tv_pick_day6.text =="Tuesday")
                {
                    day="3"
                }
                if (tv_pick_day6.text =="Wednesday")
                {
                    day="4"
                }
                if (tv_pick_day6.text =="Thursday")
                {
                    day="5"
                }
                if (tv_pick_day6.text =="Friday")
                {
                    day="6"
                }
                if (tv_pick_day6.text =="Saturday")
                {
                    day="7"
                }

            }
            R.id.pick_up_card7 -> {
                pick_up_card7.setCardBackgroundColor(Color.parseColor("#6200EE"))
                pick_up_card2.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card3.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card4.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card5.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card6.setCardBackgroundColor(Color.parseColor("#144789"))
                pick_up_card1.setCardBackgroundColor(Color.parseColor("#144789"))
                if (tv_pick_day7.text =="Sunday")
                {
                    day="1"
                }
                if (tv_pick_day7.text =="Monday")
                {
                    day="2"
                }
                if (tv_pick_day7.text =="Tuesday")
                {
                    day="3"
                }
                if (tv_pick_day7.text =="Wednesday")
                {
                    day="4"
                }
                if (tv_pick_day7.text =="Thursday")
                {
                    day="5"
                }
                if (tv_pick_day7.text =="Friday")
                {
                    day="6"
                }
                if (tv_pick_day7.text =="Saturday")
                {
                    day="7"
                }

            }

        }
    }







    private fun isValidInputs(): Boolean {
        if (list.size<1){
            showToast("Please Check atleast One Time Slot",applicationContext)
            return false
        }
        if (day.isEmpty()) {
            showToast("Please Select Date",applicationContext)
            return false
        }
        return true

    }

    private fun myObserver() {
        slotCollectionViewModel.responceTimeSlotResponce.observe(this@SlotCollectionActivity, androidx.lifecycle.Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            showToast(it.message.toString(),applicationContext)
            finish()

        })



        slotCollectionViewModel.error.observe(this@SlotCollectionActivity, androidx.lifecycle.Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            ErrorUtil.handlerGeneralError(this@SlotCollectionActivity, it)

        })
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {

            R.id.rb_nine_ten -> {
                if (isChecked==true) {
                    start_time = "9:00"
                    end_time = "10:00"
                    list.add(TimeslotModel(start_time, end_time))
                }
                if (isChecked==false){
                    start_time = "9:00"
                    end_time = "10:00"
                    list.remove(TimeslotModel(start_time, end_time))
                }
            }
            R.id.rb_ten_eleven -> {
                if (isChecked==true) {
                    start_time = "10:00"
                    end_time = "11:00"
                    list.add(TimeslotModel(start_time, end_time))
                }
                if (isChecked==false){
                    start_time = "10:00"
                    end_time = "11:00"
                    list.remove(TimeslotModel(start_time, end_time))
                }
            }
            R.id.rb_eleven_twelve -> {
                if (isChecked==true) {
                    start_time = "11:00"
                    end_time = "12:00"
                    list.add(TimeslotModel(start_time, end_time))
                }
                if (isChecked==false){
                    start_time = "11:00"
                    end_time = "12:00"
                    list.remove(TimeslotModel(start_time, end_time))
                }
            }
            R.id.rb_twelve_one -> {
                if (isChecked==true) {
                    start_time = "12:00"
                    end_time = "13:00"
                    list.add(TimeslotModel(start_time, end_time))
                }
                if (isChecked==false){
                    start_time = "12:00"
                    end_time = "13:00"
                    list.remove(TimeslotModel(start_time, end_time))
                }

            }
            R.id.rb_two_three -> {
                if (isChecked==true) {
                    start_time = "14:00"
                    end_time = "15:00"
                    list.add(TimeslotModel(start_time, end_time))
                }
                if (isChecked==false){
                    start_time = "14:00"
                    end_time = "15:00"
                    list.remove(TimeslotModel(start_time, end_time))
                }
            }
            R.id.rb_three_four -> {
                if (isChecked==true) {
                    start_time = "15:00"
                    end_time = "16:00"
                    list.add(TimeslotModel(start_time, end_time))
                }
                if (isChecked==false){
                    start_time = "15:00"
                    end_time = "16:00"
                    list.remove(TimeslotModel(start_time, end_time))
                }

            }
        }
    }
}




