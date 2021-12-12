package com.example.pp.dp

import android.provider.BaseColumns

object MyDbNameClass {
    const val TABLE_NAME = "tasks"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_TIME = "time"
    const val COLUMN_NAME_DATE = "date"

    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "PP.dp"

    const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (${BaseColumns._ID} INTEGER PRIMARY KEY," +
        "$COLUMN_NAME_TITLE TEXT, $COLUMN_NAME_TIME TEXT, $COLUMN_NAME_DATE TEXT)"

    const val SQL_DELETE_TABLE = "DROP TABLE $TABLE_NAME"


}