package com.example.pp.dp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null


    fun openDb(){
        db = myDbHelper.writableDatabase
    }

    fun insertToDb(title: String, time: String, data: String){
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_TITLE, title)
            put(MyDbNameClass.COLUMN_NAME_DATE, data)
            put(MyDbNameClass.COLUMN_NAME_TIME, time)
        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)
    }

    fun deleteFromDb(index: Int) {
        var DeleteByIndex: String = "DELETE FROM ${MyDbNameClass.TABLE_NAME} WHERE _id = $index"
        db?.execSQL(DeleteByIndex)
    }

    fun readDbData(): ArrayList<ArrayList<String>> {
        val dataList = ArrayList<ArrayList<String>>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null)
        cursor?.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE)

        while(cursor?.moveToNext()!!){
            val a = cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TITLE)
            val b = cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_DATE)
            val c = cursor.getColumnIndex(MyDbNameClass.COLUMN_NAME_TIME)
            val d = cursor.getColumnIndex("_id")
            val title = cursor.getString(a)
            val date = cursor.getString(b)
            val time = cursor.getString(c)
            val id = cursor.getString(d)
            val list = ArrayList<String>()
            list.add(date)
            list.add(time)
            list.add(title)
            list.add(id)
            dataList.add(list)
        }
        cursor.close()
        return dataList
    }

    fun closeDb(){
        myDbHelper.close()
    }
}