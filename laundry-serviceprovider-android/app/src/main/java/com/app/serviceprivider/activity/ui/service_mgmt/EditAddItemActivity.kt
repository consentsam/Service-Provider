package com.app.serviceprivider.activity.ui.service_mgmt

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
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
import kotlinx.android.synthetic.main.activity_add_item.category_item
import kotlinx.android.synthetic.main.activity_add_item.et_add_item
import kotlinx.android.synthetic.main.activity_add_item.iv_addItem_back
import kotlinx.android.synthetic.main.activity_add_item.name_item

import kotlinx.android.synthetic.main.activity_edit_add_item.*

class EditAddItemActivity : NewBaseActivity(), View.OnClickListener {

    lateinit var editItemViewModel: EditItemViewModel
    var service_type:String = ""
    var category:String = ""
    var item_id:String = ""
    var _id:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_add_item)

        init()
        initControl()
        myObserver()
    }

    override fun init() {

        editItemViewModel = ViewModelProviders.of(this).get(EditItemViewModel::class.java)
        ProgressDialogUtils.getInstance().showProgress(this, true)
        editItemViewModel.getCategoryItem()
        editItemViewModel.getAddServiceType()

      //  editItemViewModel.getItemName(category = category)

        service_type = intent.getStringExtra("service_type_id")
        category = intent.getStringExtra("category_id")
        item_id = intent.getStringExtra("item_ids")
        _id = intent.getStringExtra("ids")
    }

    override fun initControl() {

        iv_addItem_back.setOnClickListener(this)
        btn_save_item.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_addItem_back -> {

                onBackPressed()
            }

            R.id.btn_save_item -> {

                if (isValidInputs()) {

                    ProgressDialogUtils.getInstance().showProgress(this, true)
                    editItemViewModel.addEditService(

                        access_token = SharePrefUtils.getSharedInstance(this@EditAddItemActivity).accessToken!!,
                        service_type = service_type,
                        category = category,
                        item_id = item_id,
                        id = _id,
                        price = et_add_item.text.toString()

                    )
                }
            }
        }
    }

    private fun isValidInputs(): Boolean {

        if (et_add_item.getString().isEmpty()) {
            et_add_item.requestFocus()
            Toast.makeText(this,R.string.please_enter_value, Toast.LENGTH_SHORT).show()
            return false
        }

        if (!NetworkUtils.isInternetAvailable(applicationContext!!)) {
            Toast.makeText(this,R.string.error_internet, Toast.LENGTH_SHORT).show()
            return false
        }
        return true

    }

    private fun myObserver() {

        editItemViewModel.responseCategory.observe(this@EditAddItemActivity, Observer {

            ProgressDialogUtils.getInstance().hideProgress()
            setSpinnerCategory(it.response)

        })

        editItemViewModel.responseServiceType.observe(this@EditAddItemActivity, Observer {

            ProgressDialogUtils.getInstance().hideProgress()
           // setSpinnerService(it.response)

        })

        editItemViewModel.responseItemName.observe(this@EditAddItemActivity, Observer {

            ProgressDialogUtils.getInstance().hideProgress()
            setSpinnerName(it.response)

        })

        editItemViewModel.responseAddService.observe(this@EditAddItemActivity, Observer {

            ProgressDialogUtils.getInstance().hideProgress()
            editItemViewModel.getServiceItem(access_token = SharePrefUtils.getSharedInstance(this@EditAddItemActivity).accessToken!!,
                service_type = service_type)

            finish()

        })


        editItemViewModel.error.observe(this@EditAddItemActivity, Observer {

            ProgressDialogUtils.getInstance().hideProgress()
            ErrorUtil.handlerGeneralError(this@EditAddItemActivity, it)

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

        category_item.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View,
                position: Int, id: Long
            ) {

                val user: GetCategoryResponse.Response = parent!!.selectedItem as GetCategoryResponse.Response
                //displayUserData(user)
                //  Log.d("namegfdd",user.name)
                // Log.d("idgfdd",user._id)

                category = (parent?.getItemAtPosition(position) as GetCategoryResponse.Response)._id.toString()

               editItemViewModel.getItemName(category = category)


                //  Toast.makeText(this@AddItemActivity, "" + (parent?.getItemAtPosition(position) as GetCategoryResponse.Response)._id, Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {


            }
        })


    }

    /*private fun setSpinnerService(response: List<GetServiceTypeResponse.Response>?) {

        if (response != null) {
            val arrayList : ArrayList<String> = ArrayList()
            for (item in response){
                arrayList.add(item.name!!)
            }
            val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList)
            service_item.adapter = adapter

        }

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

        name_item.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
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