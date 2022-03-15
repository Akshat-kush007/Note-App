package com.example.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
//Every property that's stored in the database needs to have public visibility, which is the Kotlin default.
@Entity(tableName = "notes")
class Note(@ColumnInfo(name = "text") val text:String) {
    //Since id will auto generate ,so we don't need to add this column in constructor
    @PrimaryKey(autoGenerate = true) var idd :Int=0
}