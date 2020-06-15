package com.jlopezr.apitask

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jlopezr.apitask.Injection.Module.ViewModelFactory
import com.jlopezr.apitask.databinding.ActivityTaskListBinding
import com.jlopezr.apitask.ui.TaskViewModel
import com.jlopezr.apitask.ui.task.TaskViewModelUI

class TaskListActivity: AppCompatActivity() {
    private lateinit var binding: ActivityTaskListBinding
    private lateinit var viewModel: TaskViewModel

    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = DataBindingUtil.setContentView(this,R.layout.activity_task_list)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        viewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)
        viewModel = ViewModelProviders.of(this,ViewModelFactory(this)).get(TaskViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }
}