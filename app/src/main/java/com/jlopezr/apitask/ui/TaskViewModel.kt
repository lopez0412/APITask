package com.jlopezr.apitask.ui

import android.database.Observable
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.jlopezr.apitask.BaseViewModel
import com.jlopezr.apitask.Model.Tarea
import com.jlopezr.apitask.Model.TareaDAO
import com.jlopezr.apitask.R
import com.jlopezr.apitask.network.TaskApi
import com.jlopezr.apitask.ui.task.TaskListAdapter
import io.reactivex.Observable.fromCallable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class TaskViewModel(private val taskDao:TareaDAO): BaseViewModel() {
    @Inject
    lateinit var taskApi:TaskApi

    val taskListAdapter: TaskListAdapter = TaskListAdapter()

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        loadTasks()
    }

    private fun loadTasks(){
        subscription = io.reactivex.Observable.fromCallable { taskDao.all }
                .concatMap {
                    dbtaskList ->
                        if (dbtaskList.isEmpty()){
                            taskApi.getTasks().concatMap {
                                apitaskList -> taskDao.insertAll(*apitaskList.toTypedArray())
                                io.reactivex.Observable.just(apitaskList)
                            }
                        }else{
                            io.reactivex.Observable.just(dbtaskList)
                        }
                }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{ onRetrieveTaskListStart() }
            .doOnTerminate { onRetrieveTaskListFinish() }
            .subscribe({result -> onRetrieveTaskListSuccess(result)},{onRetrieveTaskListError()})
    }

    private fun updatetask(id:Int){
        //taskApi.updateTask(id)
    }
    // ...
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadTasks() }

    private fun onRetrieveTaskListStart(){
        loadingVisibility.value = View.VISIBLE
    }
    private fun onRetrieveTaskListFinish(){
        loadingVisibility.value = View.GONE
    }
    private fun onRetrieveTaskListSuccess(taskList:List<Tarea>){
        taskListAdapter.updatePostList(taskList)
    }
    private fun onRetrieveTaskListError(){
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}


