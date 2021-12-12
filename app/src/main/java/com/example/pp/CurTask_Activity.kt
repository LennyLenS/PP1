package com.example.pp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pp.dp.MyDbManager
import java.util.*

class CurTask_Activity : AppCompatActivity() {
    val myDbManager = MyDbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cur_task)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun normalDate(day: Int, month: Int, year: Int): String{
        var s = "$day.$month.$year"
        if(day < 10) {
            s = "0$day.$month.$year"
        }
        if(month < 10){
            s = "$day.0$month.$year"
        }
        if(day < 10 && month < 10) {
            s = "0$day.0$month.$year"
        }
        return s
    }

    fun normalTime(minute: Int, hour: Int): String{
        var s = "$hour:$minute"
        if(minute < 10) {
            s = "$hour:0$minute"
        }
        if(hour < 10){
            s = "0$hour:$minute"
        }
        if(hour < 10 && minute < 10) {
            s = "0$hour:0$minute"
        }
        return s
    }

    override fun onResume() {
        super.onResume()
        ReadDB()
    }

    fun ReadDB(){
        myDbManager.openDb()

        val dataList = myDbManager.readDbData()
        val calen = Calendar.getInstance()
        val month = calen.get(Calendar.MONTH)
        val day = calen.get(Calendar.DAY_OF_MONTH)
        val year = calen.get(Calendar.YEAR)
        val cur_day = normalDate(day, month, year)
        val hour = calen.get(Calendar.HOUR_OF_DAY)
        val minute = calen.get(Calendar.MINUTE)
        val cur_time = normalTime(minute, hour)
        var i = 1

        class task{
            var time: String = ""
            var name: String = ""
            var id: Int = 0
        }

        var arr: MutableList<task> = mutableListOf()
        for(items in dataList) {
            if(items[0] == cur_day && items[1] >= cur_time ) {
                val task_i: task = task()
                task_i.time = items[1]
                task_i.name = items[2]
                task_i.id = items[3].toInt()
                i += 1
                arr.add(task_i)
            }
        }

        if(i == 1){
            val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this)
            var ans: MutableList<String> = mutableListOf()
            ans.add("Задач на сегодня нет")
            recyclerView.adapter = CustomRecyclerAdapter(ans, cur_time, cur_day, this, { ReadDB() })
        }
        else {
            val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(this)

            var sortedByTime =  arr.sortedBy { task -> task.time }
            var j = 1
            var ans: MutableList<String> = mutableListOf()
            for(items in sortedByTime) {
                val i_time = items.time
                val i_task = items.name
                val s_now = "$j) Время: $i_time Задание: $i_task"
                ans.add(s_now)
                j += 1
            }
            recyclerView.adapter = CustomRecyclerAdapter(ans, cur_time, cur_day, this, { ReadDB() })
        }
        myDbManager.closeDb()
        //val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
    }
    override fun recreate() {
        super.recreate()
    }
    override fun onPause() {
        super.onPause()
    }
    override fun onRestart() {
        super.onRestart()
    }
    override fun onDestroy() {
        super.onDestroy()
    }

}