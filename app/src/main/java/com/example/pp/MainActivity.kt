package com.example.pp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // получаем navController
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.navFragment) as NavHostFragment? ?: return
        val navController = host.navController

        // влючаем боковое меню
        val sideBar = findViewById<NavigationView>(R.id.nav_view)
        sideBar?.setupWithNavController(navController)

        //вывод на заглавие
        val appBarConfiguration =
            AppBarConfiguration(navController.graph, findViewById(R.id.drawer_layout))
        val toolBar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar1)
        toolBar.setupWithNavController(navController, appBarConfiguration)

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun CreatePlan(view: View) {
        val create_plan_activity = Intent(this, Third_Activity::class.java)

        startActivity(create_plan_activity)
    }

    fun currentTask(view: View){
        val create_curtask_activity = Intent(this, CurTask_Activity::class.java)

        startActivity(create_curtask_activity)
    }

    fun openCalendar(view: View) {
        val create_calendar = Intent(this, Calendar_Activity::class.java)

        startActivity(create_calendar)
    }
    /*fun onClickSave(view: View){
        val myDbManager = MyDbManager(this)
        myDbManager.openDb()

        val title = findViewById<TextView>(R.id.edTitle).text.toString()
        val content = findViewById<TextView>(R.id.edContent).text.toString()
        val tvTest = findViewById<TextView>(R.id.TVText)

        myDbManager.insertToDb(title, content)

        tvTest.text = ""
        val dataList = myDbManager.readDbData()

        for(item in dataList) {
            tvTest.append(item)
        }
        myDbManager.closeDb()
    }*/
}