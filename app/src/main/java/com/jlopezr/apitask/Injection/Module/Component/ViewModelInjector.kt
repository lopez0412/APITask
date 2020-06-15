package com.jlopezr.apitask.Injection.Module.Component

import com.jlopezr.apitask.Injection.Module.NetworkModule
import com.jlopezr.apitask.ui.TaskViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(taskViewModel: TaskViewModel)
        @Component.Builder
        interface Builder{
        fun build():ViewModelInjector

            fun networkModule(networkModule: NetworkModule):Builder

    }
}