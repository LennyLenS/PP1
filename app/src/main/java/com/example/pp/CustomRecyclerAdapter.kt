package com.example.pp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pp.dp.MyDbManager


class CustomRecyclerAdapter(private var names: List<String>,
                            val cur_time: String, val cur_day: String, val context: Context, var func: () -> Unit) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var largeTextView: TextView? = null
        var buttonDelete: Button? = null

        init {
            largeTextView = itemView.findViewById(R.id.textViewLarge)
            buttonDelete = itemView.findViewById(R.id.button5)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.largeTextView?.text = names[position]
        holder.buttonDelete?.setOnClickListener() {
            Delete_task(position)
        }
        if (names[position] == "Задач на сегодня нет") {
            holder.buttonDelete?.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount() = names.size


    fun Delete_task(index: Int) {
        val myDbManager = MyDbManager(context)
        myDbManager.openDb()

        var dataList = myDbManager.readDbData()
        var i = 1

        class task {
            var time: String = ""
            var name: String = ""
            var id: Int = 0
        }

        var arr: MutableList<task> = mutableListOf()
        for (items in dataList) {
            if (items[0] == cur_day && items[1] >= cur_time) {
                val task_i: task = task()
                task_i.time = items[1]
                task_i.name = items[2]
                task_i.id = i
                i += 1
                arr.add(task_i)
            }
        }

        var sortedByTime = arr.sortedBy { task -> task.time }

        myDbManager.deleteFromDb(sortedByTime[index].id)

        func()


        //CurTask_Activity().Delete_task()
    }
}
