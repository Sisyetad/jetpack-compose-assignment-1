package com.example.acadamiccourses.data.model

data class Course(
    val courseCode: String,
    val courseTitle: String,
    val category: String,
    val credit: String,
    val description: String,
    val prerequest: String
)
