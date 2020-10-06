package com.app.serviceprivider.activity.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.serviceprivider.R
import com.app.serviceprivider.activity.ui.WorkUnderDevActivity
import com.app.serviceprivider.activity.ui.coll_delivery_mgmt.CollDelMgmtActivity
import com.app.serviceprivider.activity.ui.order_request_management.OrderManagementActivity
import com.app.serviceprivider.activity.ui.service_mgmt.ServiceManagementActivity
import com.app.serviceprivider.activity.ui.task.TaskActivity
import com.app.serviceprivider.adapters.OfflineDashboardAdapter
import com.app.serviceprivider.data.OnlineStaticData
import com.app.serviceprivider.adapters.OnlineDashboardAdapter
import com.app.serviceprivider.data.OfflineStaticData
import com.app.serviceprivider.ui.activity.login.LoginActivity
import com.app.serviceprivider.utils.ErrorHandlingClass
import com.app.serviceprivider.utils.SharePrefUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.singlevendor.base.NewBaseActivity
import com.singlevendor.utils.ProgressDialogUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : NewBaseActivity() , NavigationView.OnNavigationItemSelectedListener,View.OnClickListener,
    BottomNavigationView.OnNavigationItemSelectedListener {


    private lateinit var onlineDashAdapter: OnlineDashboardAdapter
    private lateinit var offlineDashAdapter: OfflineDashboardAdapter

    // lateinit var dashboard_toggle_btn: ImageView
    private lateinit var viewOnlineDashModel: MainViewModel
   // private lateinit var nav_view: NavigationView;
   private lateinit var bottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // nav_view = findViewById(R.id.nav_view)

        init()
        initControl()
        myObserver()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun init() {
        viewOnlineDashModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // ProgressDialogUtils.getInstance().showProgress(this,true)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = " "
        setSupportActionBar(toolbar)

       bottomNavigation = findViewById(R.id.bottom_nav)
        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        navView.setNavigationItemSelectedListener(this)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
        onlineRecyclerView()
        offlineRecyclerView()
        addDataSet()



        viewOnlineDashModel.getOnlineDash(

            access_token = SharePrefUtils.getSharedInstance(this@MainActivity).accessToken!!,
            bank = ""
        )

    }

    override fun initControl() {

        dashboard_toggle_btn.setOnClickListener(this)
        tv_online.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.dashboard_toggle_btn -> {

                if (!dashboard_toggle_btn.isActivated()) {

                    dashboard_toggle_btn.setActivated(!dashboard_toggle_btn.isActivated())
                    rl_online.visibility = View.VISIBLE
                    cl_offline.visibility = View.GONE
                    btn_view_order.visibility = View.VISIBLE
                } else if (dashboard_toggle_btn.isActivated()) {

                    dashboard_toggle_btn.setActivated(!dashboard_toggle_btn.isActivated())
                    rl_online.visibility = View.GONE
                    cl_offline.visibility = View.VISIBLE
                    btn_view_order.visibility = View.GONE
                }

            }
            R.id.tv_online -> {

                popUpMenu()
            }

        }
    }

    private fun popUpMenu() {

        val popupMenu: PopupMenu = PopupMenu(this,tv_online)

        popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
        popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when(item.itemId) {
                R.id.menu_online ->{


                }

                R.id.menu_busy ->{


                }

                R.id.menu_closed ->{

                }

            }
            true
        })
        popupMenu.show()

}

    /*private fun customDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog_layout)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btn_apply = dialog.findViewById(R.id.btn_apply) as Button
        // val btn_disagree = dialog.findViewById(R.id.tv_done) as Button
        //val tvTitle: TextView = dialog.findViewById(R.id.title)
        // val tvDescription: TextView = dialog.findViewById(R.id.description)
        // val ivIcon: ImageView = dialog.findViewById(R.id.icon)


        btn_apply.setOnClickListener {

            *//* val intent = Intent(this@AddAddressActivity, SelectAddressActivity::class.java)
            startActivity(intent)*//*
            // finish()
            dialog.dismiss()
        }

        *//* btn_disagree.setOnClickListener {
             this.finish();
         }*//*

        dialog.show()
    }*/

    private fun addDataSet() {
        val data = OnlineStaticData.createDataSet()
        val offlineData = OfflineStaticData.createDataSet()
        onlineDashAdapter.submitList(data)
        offlineDashAdapter.submitList(offlineData)
    }

    private fun onlineRecyclerView() {


        rv_onlineDash_list.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            onlineDashAdapter = OnlineDashboardAdapter()
            adapter = onlineDashAdapter

        }

    }

    private fun offlineRecyclerView() {

        /*  rv_size.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)*/
        rv_OfflineDash_list.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

            offlineDashAdapter = OfflineDashboardAdapter()
            adapter = offlineDashAdapter
        }
    }





    private fun myObserver() {

        viewOnlineDashModel.resOnlineDashboard.observe(this, Observer {
           // it.message?.let { it1 -> showToast(it1) }
            ProgressDialogUtils.getInstance().hideProgress()



        })
        viewOnlineDashModel.mError.observe(this, Observer {
            ProgressDialogUtils.getInstance().hideProgress()
            ErrorHandlingClass.errorHandlingException(this, it)

        })

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_task -> {

                Log.e("my check1", "dashboard")
                startActivity(Intent(this@MainActivity, TaskActivity::class.java))
                // Toast.makeText(this,"Dashboard", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.nav_CAMS -> {
                Log.e("my check", "dashboard")
                startActivity(Intent(this@MainActivity, CollDelMgmtActivity::class.java))
               // Toast.makeText(this,"Dashboard", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.nav_service_mgmt -> {

                Log.e("my check", "nav_service_man")
                startActivity(Intent(this@MainActivity, ServiceManagementActivity::class.java))
                return true
            }
            R.id.nav_orm -> {

                Log.e("my check", "nav_service_man")
                startActivity(Intent(this@MainActivity, OrderManagementActivity::class.java))
                return true
            }
            R.id.nav_payment_management -> {

                Log.e("my check", "nav_service_man")
                startActivity(Intent(this@MainActivity, WorkUnderDevActivity::class.java))
                return true
            }
            R.id.nav_SDM -> {

                Log.e("my check", "nav_service_man")
                startActivity(Intent(this@MainActivity, WorkUnderDevActivity::class.java))
                return true
            }
            R.id.nav_setting -> {

                Log.e("my check", "nav_service_man")
                startActivity(Intent(this@MainActivity, WorkUnderDevActivity::class.java))
                return true
            }
            R.id.nav_signout -> {

                Log.e("my check", "nav_signout")

                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                SharePrefUtils.getSharedInstance(this).deletePreferences()
                finishAffinity()
                return true
            }
            else -> return false
        }
    }
}



