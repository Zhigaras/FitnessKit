package com.zhigaras.fitnesskit.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

class ScheduleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    
    private val viewMap: MutableMap<Int, View> = HashMap()
    
    init {
        findViews(view)
    }
    
    fun setText(@IdRes id: Int, text: String) {
        val view =
            (viewMap[id] ?: throw IllegalArgumentException("View for $id not found")) as? TextView
                ?: throw IllegalArgumentException("View for $id is not a TextView")
        view.text = text
    }
    
    private fun findViews(itemView: View) {
        addToMap(itemView)
        if (itemView is ViewGroup) {
            val childCount = itemView.childCount
            (0 until childCount)
                .map { itemView.getChildAt(it) }
                .forEach { findViews(it) }
        }
    }
    
    private fun addToMap(itemView: View) {
        if (itemView.id == View.NO_ID) {
            itemView.id = View.generateViewId()
        }
        viewMap[itemView.id] = itemView
    }
}