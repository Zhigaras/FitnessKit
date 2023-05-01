package com.zhigaras.fitnesskit.ui.adapter

import com.zhigaras.fitnesskit.domain.entitys.Lesson

data class LessonListItem(
    val itemType: ItemType,
    val header: String? = null,
    val lesson: Lesson? = null
)

enum class ItemType {
    HEADER,
    LESSON
}