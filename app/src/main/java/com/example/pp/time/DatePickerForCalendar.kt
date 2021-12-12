package com.example.pp.time

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import com.example.pp.R
import java.util.*

class DatePickerFragment2() : DialogFragment(), TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {
    private lateinit var calendar:Calendar
    var date: String = ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Initialize a Calendar instance
        calendar = Calendar.getInstance()

        // Get the system current day, month, year

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        // Create a DatePickerDialog with system current time
        // Return the DatePickerDialog
        return DatePickerDialog(
            activity,
            android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,
            this,
            year,
            month,
            day)
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

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val tv:TextView = activity.findViewById(R.id.textView4)
        val s = normalDate(day, month, year)
        tv.text = s
        //Calendar_Activity().Show_task()
        /*
        val tv:TextView = activity.findViewById(R.id.textView4) as TextView
        val s = normalDate(day, month, year)
        tv.text = s


        val myDbManager = MyDbManager(activity)
        myDbManager.openDb()

        val tvTest = activity.findViewById<TextView>(R.id.textViewCalendar)
        tvTest.text = ""
        val dataList = myDbManager.readDbData()
        val str = s
        var i = 1
        class task{
            var time: String = ""
            var name: String = ""
            var id: Int = 0
        }

        var arr: MutableList<task> = mutableListOf()
        for(items in dataList) {
            if(items[0] == str) {
                val task_i: task = task()
                task_i.time = items[1]
                task_i.name = items[2]
                task_i.id = i
                i += 1
                arr.add(task_i)
                /*
                tvTest.append("$i) Время:")
                tvTest.append(items[1])
                tvTest.append(" Задание: ")
                tvTest.append(items[2])
                i += 1
                tvTest.append("\n")
                */
            }
        }
        if(i == 1){
            tvTest.text = "На это число нет задач."
        }
        else {
            var sortedByTime =  arr.sortedBy { task -> task.time }
            var j = 1
            for(items in sortedByTime) {
                tvTest.append("$j) Время:")
                tvTest.append(items.time)

                tvTest.append(" Задание: ")
                tvTest.append(items.name)

                tvTest.append("\n")

                j += 1
            }
        }
        myDbManager.closeDb()
        */
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // Do something with the returned time
        val tv: TextView = activity.findViewById(R.id.textView4) as TextView
        val s = "Hour : Minute\n $hourOfDay:$minute"
        tv.text = s
    }


    // When user cancel the time picker dialog
    override fun onCancel(dialog: DialogInterface?) {
        Toast.makeText(activity,"Picker Canceled.",Toast.LENGTH_SHORT).show()
        super.onCancel(dialog)
    }

}