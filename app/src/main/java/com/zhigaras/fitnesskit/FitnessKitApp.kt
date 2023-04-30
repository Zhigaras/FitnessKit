package com.zhigaras.fitnesskit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.zhigaras.fitnesskit.data.core.Core
import com.zhigaras.fitnesskit.ui.ViewModelFactory

class FitnessKitApp : Application(), ProvideViewModel {
    
    private lateinit var viewModelFactory: ViewModelFactory
    
    override fun onCreate() {
        super.onCreate()
        
        val core = Core.Base()
        viewModelFactory = ViewModelFactory(core)
        
    }
    
    override fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, viewModelFactory)[clazz]
}

interface ProvideViewModel {
    
    fun <T : ViewModel> provideViewModel(clazz: Class<T>, owner: ViewModelStoreOwner): T
}
