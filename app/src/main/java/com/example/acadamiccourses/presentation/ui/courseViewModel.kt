package com.example.acadamiccourses.presentation

import androidx.lifecycle.ViewModel
import com.example.acadamiccourses.data.model.CourseRepository
import com.example.acadamiccourses.presentation.ui.CourseItemState
import com.example.acadamiccourses.presentation.ui.CourseListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CourseViewModel(
    private val repository: CourseRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CourseListState())
    val state: StateFlow<CourseListState> = _state.asStateFlow()

    init {
        loadCourses()
    }

    private fun loadCourses() {
        val courses = repository.getDummyCourses().map { CourseItemState(it) }
        _state.value = CourseListState(courses = courses)
    }

    fun toggleExpanded(courseKey: Int) {
        _state.value = _state.value.copy(
            courses = _state.value.courses.map {
                if (it.course.courseCode.hashCode() == courseKey) {
                    it.copy(isExpanded = !it.isExpanded)
                } else it
            }
        )
    }
}
