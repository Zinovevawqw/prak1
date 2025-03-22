package com.example.prack

import android.hardware.display.DeviceProductInfo
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import kotlinx.coroutines.channels.ProducerScope
import kotlin.reflect.KClass

class ViewModelLazy <VM : ViewModel> (
    private val viewModelClass: KClass<VM>,
    private val storeProducer: ()-> ViewModelStore,
    private val factoryProducer: () -> ViewModelProvider.Factory
    ): Lazy<VM>{
        private var cached: VM? = null
        override val value: VM
            get(){
                val viewModel = cached
                return if (viewModel == null){
                    val factory = factoryProducer()
                    val store = storeProducer()
                    ViewModelProvider(store, factory).get(viewModelClass.java).also {
                        cached = it
                    }
                } else{
                    viewModel
                }
            }

    override fun isInitialized() = cached!= null
    }
