package com.app.serviceprivider.activity.ui.service_mgmt

import NetworkUtils
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.serviceprivider.R
import com.app.serviceprivider.model.GetCategoryResponse
import com.app.serviceprivider.model.GetItemNameResponse
import com.app.serviceprivider.utils.ErrorUtil
import com.app.serviceprivider.utils.SharePrefUtils
import com.app.serviceprivider.utils.getString
import com.singlevendor.base.NewBaseActivity
import com.singlevendor.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.activity_add_item.*


class AddItemActivity : NewBaseActivity(),View.OnClickListener {

    lateinit var addItemViewModel: AddItemViewModel

    var service_type:String = ""
    var category:String = ""
    var category_id:String = ""
    var item_id:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        init()
        initControl()
        myObserver()
    }

    override fun init() {

      service_type = intent.getStringExtra("service_type")
        // category_id = intent.getStringExtra("category")
        //  item_id = intent.getStringExtra("item_id")

        addItemViewModel = ViewModelProviders.of(this).get(AddItemViewModel::class.java)
        ProgressDialogUtils.getInstance().showProgress(this, true)

        addItemViewModel.getCategoryItem()
        addItemViewModel.getAddServiceType()


    }

    override fun initControl() {

        iv_addItem_back.setOnClickListener(this)
        btn_add_item.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_addItem_back -> {

                onBackPressed()
            }

            R.id.btn_add_item -> {


                // Log.d("dese",service_type)
                // Log.d("dese", category )
                // Log.d("dese",item_id)

                if (isValidInputs()) {

                    ProgressDialogUtils.getInstance().showProgress(this, true)
                    addItemViewModel.addService(

                        access_token = SharePrefUtils.getSharedInstance(this@AddItemActivity).accessToken!!,
                        service_type = service_type,
                        category = category,
                        item_id = item_id,
                        price = et_add_item.text.toString()

                    )


                }
            }
        }
    }

    private fun isValidInputs(): Boolean {

        if (et_add_item.getString().isEmpty()) {
            et_add_item.requestFocus()
            Toast.makeText(this,R.string.please_enter_value,Toast.LENGTH_SHORT).show()
            return false
        }

        if (!NetworkUtils.isInternetAvailable(applicationContext!!)) {
            Toast.makeText(this,R.string.error_internet,Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    private fun myObserver() {

        addItemViewModel.responseCategory.observe(this@AddItemActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            setSpinnerCategory(it.response)

            // category = it.response!![0]._id.toString()
        })

        addItemViewModel.responseServiceType.observe(this@AddItemActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
        //    setSpinnerService(it.response)

            //  service_type = it.response!![0]._id.toString()

        })

        addItemViewModel.responseItemName.observe(this@AddItemActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            setSpinnerName(it.response)

            /*   if (it.response!= null){

                   val serviceItemList:ArrayList<String> = ArrayList()

                   for (item in it.response!!){

                       // serviceItemList.add(item.service_type!!)

                       item_id =serviceItemList.add(item._id!!).toString()
                   }
               }*/

            //  item_id = it.response!![0]?._id.toString()

        })

        addItemViewModel.responseAddService.observe(this@AddItemActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()

            addItemViewModel.getServiceItem(access_token = SharePrefUtils.getSharedInstance(this@AddItemActivity).accessToken!!,
                service_type = service_type)

            val intent=Intent()
            setResult(Activity.RESULT_OK,intent)
            finish()

        })


        addItemViewModel.error.observe(this@AddItemActivity, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            ErrorUtil.handlerGeneralError(this@AddItemActivity, it)

        })


    }
    private fun setSpinnerCategory(response: List<GetCategoryResponse.Response>?) {
        var posnew: String = ""
        val arrayList : ArrayList<String> = ArrayList()


        if (response != null) {

            val adapter: ArrayAdapter<GetCategoryResponse.Response> = ArrayAdapter<GetCategoryResponse.Response>(
                this,
                android.R.layout.simple_spinner_item, response
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            category_item.setAdapter(adapter)





        }

        category_item.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View,
                position: Int, id: Long
            ) {

                val user: GetCategoryResponse.Response = parent!!.selectedItem as GetCategoryResponse.Response
                //displayUserData(user)
                //  Log.d("namegfdd",user.name)
                // Log.d("idgfdd",user._id)

                category = (parent?.getItemAtPosition(position) as GetCategoryResponse.Response)._id.toString()

                addItemViewModel.getItemName(category = category)


                //  Toast.makeText(this@AddItemActivity, "" + (parent?.getItemAtPosition(position) as GetCategoryResponse.Response)._id, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {


            }
        })


    }

    /*private fun setSpinnerService(response: List<GetServiceTypeResponse.Response>?) {

        if (response != null) {

            val adapter: ArrayAdapter<GetServiceTypeResponse.Response> = ArrayAdapter<GetServiceTypeResponse.Response>(
                this,
                android.R.layout.simple_spinner_item, response
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            service_item.setAdapter(adapter)

        }

        service_item.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View,
                position: Int, id: Long
            ) {

                val user: GetServiceTypeResponse.Response = parent!!.selectedItem as GetServiceTypeResponse.Response
                //displayUserData(user)
                // Log.d("namegfdd",user.name)
                //Log.d("idgfdd",user._id)

                service_type = (parent?.getItemAtPosition(position) as GetServiceTypeResponse.Response)._id.toString()

                //  Toast.makeText(this@AddItemActivity, "" + (parent?.getItemAtPosition(position) as GetServiceTypeResponse.Response)._id, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {


            }
        })



    }*/

    private fun setSpinnerName(response: List<GetItemNameResponse.Response>?) {

        if (response != null) {
            /* val arrayList : ArrayList<String> = ArrayList()
             for (item in response){
                 arrayList.add(item.name!!)

                // item_id =arrayList.add(item._id!!).toString()
             }
             val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList)
             name_item.adapter = adapter*/

            val adapter: ArrayAdapter<GetItemNameResponse.Response> = ArrayAdapter<GetItemNameResponse.Response>(
                this,
                android.R.layout.simple_spinner_item, response
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            name_item.setAdapter(adapter)

        }

        name_item.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View,
                position: Int, id: Long
            ) {

                val user: GetItemNameResponse.Response = parent!!.selectedItem as GetItemNameResponse.Response
                //displayUserData(user)
                Log.d("namegfdd",user.name)
                Log.d("idgfdd",user._id)

                item_id = (parent?.getItemAtPosition(position) as GetItemNameResponse.Response)._id.toString()

                //  Toast.makeText(this@AddItemActivity, "" + (parent?.getItemAtPosition(position) as GetItemNameResponse.Response)._id, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {


            }
        })


    }

}
