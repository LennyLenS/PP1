package com.example.pp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pp.dp.MyDbManager
import com.example.pp.time.DatePickerFragment2

class Calendar_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
    }

    override fun onResume() {
        super.onResume()


    }

    fun openSMT(view: View) {
        Show_task()
    }
    fun openCalendar(view: View) {
        val newFragment = DatePickerFragment2()
        // Show the time picker dialog
        newFragment.show(fragmentManager, "Date")
    }

    fun Show_task() {
        val day: String = findViewById<TextView>(R.id.textView4).text.toString()

        val myDbManager = MyDbManager(this)
        myDbManager.openDb()

        val dataList = myDbManager.readDbData()

        class task {
            var time: String = ""
            var name: String = ""
            var id: Int = 0
        }

        var arr: MutableList<task> = mutableListOf()
        var i = 1

        for (items in dataList) {
            if (items[0] == day) {
                val task_i: task = task()
                task_i.time = items[1]
                task_i.name = items[2]
                task_i.id = items[3].toInt()
                i += 1
                arr.add(task_i)
            }
        }

        if (i == 1) {
            val recyclerView: RecyclerView = findViewById(R.id.recyclerView2)
            recyclerView.layoutManager = LinearLayoutManager(this)
            var ans: MutableList<String> = mutableListOf()
            ans.add("Задач на сегодня нет")
            recyclerView.adapter = CustomRecyclerAdapter2(ans, "00:00", day, this, { Show_task() })
        } else {
            val recyclerView: RecyclerView = findViewById(R.id.recyclerView2)
            recyclerView.layoutManager = LinearLayoutManager(this)

            var sortedByTime = arr.sortedBy { task -> task.time }
            var j = 1
            var ans: MutableList<String> = mutableListOf()
            for (items in sortedByTime) {
                val i_time = items.time
                val i_task = items.name
                val s_now = "$j) Время: $i_time Задание: $i_task"
                ans.add(s_now)
                j += 1
            }
            recyclerView.adapter = CustomRecyclerAdapter2(ans, "00:00", day, this, { Show_task() })
        }
        myDbManager.closeDb()
    }

}