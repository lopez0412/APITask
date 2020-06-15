package com.jlopezr.apitask.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Tarea(
    @PrimaryKey val taskNumber:Int,
    val taskName:String,
    val taskCreateDate:String,
    val taskArea:String,
    val taskDescription:String,
    val taskDueDate:String,
    val taskPriority:String,
    var taskUser: String,
    var taskImage:String,
    var taskStatus:Int)