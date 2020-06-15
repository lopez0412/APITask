package com.jlopezr.apitask.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TareaDAO{
    @get:Query("Select * From tarea")
    val all:List<Tarea>

    @Query("Select * From tarea where taskNumber=:Number")
    fun loaddetail(Number:Int):LiveData<Tarea>

    @Insert
    fun insertAll(vararg tareas: Tarea)
}