package com.jlopezr.apitask

import android.app.Service
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jlopezr.apitask.ui.TaskViewModel
import kotlinx.android.synthetic.main.activity_task_detail.*

class TaskDetailActivity : AppCompatActivity() {

    var Status=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        var intent = intent
        var taskNumberInt = intent.getIntExtra("taskNumber",0)
        var taskNameExtra = intent.getStringExtra("taskName")
        var taskCreateDateExtra = intent.getStringExtra("taskCreateDate")
        var taskArea = intent.getStringExtra("taskArea")
        var taskPriori = intent.getStringExtra("taskPriority")
        var taskDescrip = intent.getStringExtra("taskDescription")
        var taskstatus = intent.getIntExtra("taskStatus",1)

        txttaskId.text = "Id: $taskNumberInt"
        txttaskNameDet.text = "Titulo: $taskNameExtra"
        txttaskDateCreateDet.text = "Fecha Creación: $taskCreateDateExtra"
        txttaskArea.text = "Area: $taskArea"
        txttaskDescrip.text = "Descripcion: $taskDescrip"
        txttaskPriori.text = "Importancia: $taskPriori"

        if (taskstatus==1){
            radio1.isChecked = true
        }else if (taskstatus==2){
            radio2.isChecked = true
        }

        rgstatus.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId==R.id.radio1){
                Status = 1
            }else if (checkedId==R.id.radio2){
                Status = 2
            }
        }
        btnActualizar.setOnClickListener {

        }
    }
}