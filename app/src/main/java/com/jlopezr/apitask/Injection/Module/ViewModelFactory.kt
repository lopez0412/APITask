package com.jlopezr.apitask.Injection.Module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.jlopezr.apitask.Model.database.AppDataBase
import com.jlopezr.apitask.ui.TaskViewModel
import java.lang.IllegalArgumentException


class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDataBase::class.java,"tareas").build()
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(db.taskDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}