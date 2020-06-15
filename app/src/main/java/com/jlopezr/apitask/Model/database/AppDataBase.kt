package com.jlopezr.apitask.Model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jlopezr.apitask.Model.Tarea
import com.jlopezr.apitask.Model.TareaDAO

@Database(entities = [Tarea::class],version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun taskDao():TareaDAO
}