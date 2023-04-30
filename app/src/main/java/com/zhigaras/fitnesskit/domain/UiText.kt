package com.zhigaras.fitnesskit.domain

import android.content.Context
import androidx.annotation.StringRes

interface UiText {
    
    fun asString(context: Context): String
    
    class DynamicString(private val value: String) : UiText {
        override fun asString(context: Context): String {
            return value
        }
    }
    
    class ResourceString(
        @StringRes private val resId: Int,
        private vararg val args: Any
    ) : UiText {
        override fun asString(context: Context): String {
            return context.getString(resId, *args)
        }
    }
}
