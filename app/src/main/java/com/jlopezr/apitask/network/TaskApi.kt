package com.jlopezr.apitask.network


import com.jlopezr.apitask.Model.Tarea
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path


interface TaskApi {

    @GET("api/task")
    fun getTasks(): Observable<List<Tarea>>

    @PUT("api/task/{id}")
    fun updateTask(@Path("id") id:Int)

}