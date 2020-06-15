package com.jlopezr.apitask

import androidx.lifecycle.ViewModel
import com.jlopezr.apitask.Injection.Module.Component.DaggerViewModelInjector
import com.jlopezr.apitask.Injection.Module.Component.ViewModelInjector
import com.jlopezr.apitask.Injection.Module.NetworkModule
import com.jlopezr.apitask.ui.TaskViewModel
import dagger.internal.SetFactory.builder


abstract class BaseViewModel :ViewModel(){

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is TaskViewModel -> injector.inject(this)
        }
    }
}