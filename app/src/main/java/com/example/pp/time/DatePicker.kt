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

class DatePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {
    private lateinit var calendar:Calendar
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Initialize a Calendar instance
        calendar = Calendar.getInstance()

        // Get the system current hour and minute
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

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


    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // Do something with the returned time
        val tv:TextView = activity.findViewById(R.id.timeView) as TextView
        var s = "Hour : Minute\n $hourOfDay:$minute"
        if(minute < 10) {
            s = "Hour : Minute\n $hourOfDay:0$minute"
        }
        tv.text = s

    }


    // When user cancel the time picker dialog
    override fun onCancel(dialog: DialogInterface?) {
        Toast.makeText(activity,"Picker Canceled.",Toast.LENGTH_SHORT).show()
        super.onCancel(dialog)
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
        val tv:TextView = activity.findViewById(R.id.dateView) as TextView
        val s = normalDate(day, month, year)
        tv.text = s
    }
}