package com.jlopezr.apitask.ui.task

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.jlopezr.apitask.BaseViewModel
import com.jlopezr.apitask.Model.Tarea
import com.jlopezr.apitask.TaskDetailActivity

class TaskViewModelUI:BaseViewModel() {
    private val taskNumber = MutableLiveData<Int>()
    private val taskName = MutableLiveData<String>()
    private val taskCreateDate = MutableLiveData<String>()
    private val taskPriority = MutableLiveData<String>()







    fun bind(task:Tarea){
        taskName.value = task.taskName
        taskCreateDate.value = task.taskCreateDate
        taskNumber.value = task.taskNumber
        taskPriority.value = task.taskPriority
    }

    fun getTaskName():MutableLiveData<String>{
        return  taskName
    }
    fun getTaskCreateDate():MutableLiveData<String>{
        return  taskCreateDate
    }
    fun getTaskNumber():MutableLiveData<Int>{
        return  taskNumber
    }
    fun getTaskPriority():MutableLiveData<String>{
        return  taskPriority
    }

fun onclickItem(task:Tarea,view:View){
    var context = view.context
    var intent = Intent(context,TaskDetailActivity::class.java)
    intent.putExtra("taskNumber",task.taskNumber)
    intent.putExtra("taskName",task.taskName)
    intent.putExtra("taskCreateDate",task.taskCreateDate)
    intent.putExtra("taskArea",task.taskArea)
    intent.putExtra("taskDescription",task.taskDescription)
    intent.putExtra("taskPriority",task.taskPriority)
    intent.putExtra("taskStatus",task.taskStatus)
    //intent.putExtra("")
    context.startActivity(intent)
}

}