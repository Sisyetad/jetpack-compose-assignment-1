package com.example.acadamiccourses.presentation.ui

import com.example.acadamiccourses.data.model.Course

data class CourseListState(
    val courses: List<CourseItemState> = emptyList()
)

data class CourseItemState(
    val course: Course,
    val isExpanded: Boolean = false
)
