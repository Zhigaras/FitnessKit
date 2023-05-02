package com.zhigaras.fitnesskit.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhigaras.fitnesskit.R

class ScheduleAdapter : RecyclerView.Adapter<ScheduleViewHolder>() {
    
    private var itemList: List<LessonListItem> = emptyList()
    
    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<LessonListItem>) {
        itemList = data
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val viewToInflate =
            if (viewType == ItemType.HEADER.ordinal) R.layout.header_item else R.layout.lesson_item
        return ScheduleViewHolder(
            LayoutInflater.from(parent.context).inflate(viewToInflate, parent, false)
        )
    }
    
    override fun getItemCount(): Int = itemList.size
    
    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val item = itemList[position]
        item.lesson?.let {
            val viewList = listOf(
                R.id.start_time,
                R.id.lesson_name,
                R.id.duration,
                R.id.end_time,
                R.id.couch_name,
                R.id.location
            )
            val valueList = listOf(
                it.startTime,
                it.lessonName,
                it.duration().asString(holder.itemView.context),
                it.endTime,
                it.coachName.asString(holder.itemView.context),
                it.place
            )
            viewList.zip(valueList).forEach { pair ->
                holder.setText(pair.first, pair.second)
            }
            holder.itemView.findViewById<TextView>(R.id.color_marker)
                .setBackgroundColor(it.markerColor)
        }
        item.header?.let {
            holder.setText(R.id.lesson_header, it)
        }
    }
    
    override fun getItemViewType(position: Int): Int {
        return itemList[position].itemType.ordinal
    }
}