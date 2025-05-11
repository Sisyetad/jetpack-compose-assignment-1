package com.example.acadamiccourses.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.acadamiccourses.presentation.component.CourseCard
import com.example.acadamiccourses.presentation.component.SearchAppBar
import com.example.acadamiccourses.ui.theme.AcademicAppBar

@Composable
fun CourseListScreen(
    courseListState: CourseListState,
    onItemClick: (Int) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }

    val filteredCourses = remember(courseListState.courses, searchQuery) {
        if (searchQuery.isBlank()) {
            courseListState.courses
        } else {
            courseListState.courses.filter { courseItem ->
                courseItem.course.courseTitle.contains(searchQuery, ignoreCase = true) ||
                        courseItem.course.courseCode.contains(searchQuery, ignoreCase = true) ||
                        courseItem.course.category.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            if (isSearchActive) {
                SearchAppBar(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { searchQuery = it },
                    onCloseSearch = {
                        searchQuery = ""
                        isSearchActive = false
                    },
                )
            } else {
                AcademicAppBar(
                    title = "Explore Courses",
                    backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                    actions = {
                        IconButton(
                            onClick = { isSearchActive = true },
                            modifier = Modifier.semantics {
                                this.contentDescription = "Open search"
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Courses",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            items(filteredCourses) { courseItem ->
                CourseCard(
                    state = courseItem,
                    onCardClick = {
                        onItemClick(courseItem.course.courseCode.hashCode())
                    }
                )
            }
        }
    }
}
