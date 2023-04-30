package com.zhigaras.fitnesskit.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

interface Communication {
    
    interface Map<T : Any> {
        
        fun map(data: T)
        
    }
    
    interface Observe<T : Any> {
        
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
        
    }
    
    interface Mutable<T : Any> : Observe<T>, Map<T>
    
    class Base<T : Any>(
        private val liveData: MutableLiveData<T> = MutableLiveData()
    ) : Mutable<T> {
        
        override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
            liveData.observe(owner, observer)
        
        override fun map(data: T) {
            liveData.value = data
        }
    }
}