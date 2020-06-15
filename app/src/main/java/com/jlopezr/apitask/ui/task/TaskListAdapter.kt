package com.jlopezr.apitask.ui.task

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jlopezr.apitask.Model.Tarea
import com.jlopezr.apitask.R
import com.jlopezr.apitask.TaskDetailActivity
import com.jlopezr.apitask.databinding.TaskRowBinding

class TaskListAdapter:RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
    private lateinit var taskList:List<Tarea>


    class ViewHolder(private val binding: TaskRowBinding):RecyclerView.ViewHolder(binding.root) {
         private val viewModel = TaskViewModelUI()

        fun bind(task:Tarea){
            // ...
            viewModel.bind(task)
            binding.viewModel = viewModel
        }

        fun onclick(task: Tarea,view:View){
            viewModel.onclickItem(task,view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: TaskRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.task_row, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::taskList.isInitialized) taskList.size else 0
    }

    fun updatePostList(taskList:List<Tarea>){
        this.taskList = taskList
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskList[position])

        holder.itemView.setOnClickListener {
            //
            holder.onclick(taskList[position],holder.itemView)

            //var intent = Intent(this,TaskDetailActivity::class.java)


        }
    }
}