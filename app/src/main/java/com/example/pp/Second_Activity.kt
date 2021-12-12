package com.example.pp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Second_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun CreateTask(view: View) {
        val create_plan_activity = Intent(this, Third_Activity::class.java)

        startActivity(create_plan_activity)
    }
}