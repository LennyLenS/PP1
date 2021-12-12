package com.example.pp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pp.dp.MyDbManager
import com.example.pp.time.DatePickerFragment
import com.example.pp.time.TimePickerFragment

class Third_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }

    fun PickTime(view: View) {
        // Initialize a new TimePickerFragment
        val newFragment = TimePickerFragment()
        // Show the time picker dialog
        newFragment.show(fragmentManager, "Время")
    }

    fun PickDate(view: View) {
        // Initialize a new TimePickerFragment
        val newFragment = DatePickerFragment()
        // Show the date picker dialog
        newFragment.show(fragmentManager, "Дата")
    }

    fun SaveTask(view: View) {
        Toast.makeText(baseContext, "Укажите название задачи!", Toast.LENGTH_LONG).show()
        val title = findViewById<TextView>(R.id.inputNameTask).text.toString()
        val time = findViewById<TextView>(R.id.timeView).text.toString()
        val data = findViewById<TextView>(R.id.dateView).text.toString()
        var flag: Boolean = false
        // not empty title
        for(ch in title){
            if(ch != ' '){
                flag = true
            }
        }
        Log.d("flag", flag.toString())
        // if title empty
        if(!flag) {
            val toast = Toast.makeText(this, "Укажите название задачи!", Toast.LENGTH_LONG)
            Log.d("flag", flag.toString())
            //toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
            return
        }

        if(time == "Время" || data == "Дата"){
            val toast = Toast.makeText(this, "Укажите дату/время задачи!", Toast.LENGTH_SHORT)
            //toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
            return
        }
        val myDbManager = MyDbManager(this)
        myDbManager.openDb()

        myDbManager.insertToDb(title, time, data)

        myDbManager.closeDb()

        val toast = Toast.makeText(this, "Задача создана!", Toast.LENGTH_SHORT)
        //toast.setGravity(Gravity.CENTER, 0, 0)
        val color = toast.getView()
        val red = Color.red(1)
        color?.setBackgroundColor(red)
        toast.show()
        findViewById<TextView>(R.id.inputNameTask).text = ""
        findViewById<TextView>(R.id.timeView).text = "Время"
        findViewById<TextView>(R.id.dateView).text = "Дата"

    }
}