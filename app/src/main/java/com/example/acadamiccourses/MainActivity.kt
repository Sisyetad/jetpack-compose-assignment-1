package com.example.acadamiccourses

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.acadamiccourses.data.model.CourseRepository
import com.example.acadamiccourses.presentation.CourseViewModel
import com.example.acadamiccourses.presentation.ui.CourseListScreen
import com.example.acadamiccourses.ui.theme.AcadamicCourseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val viewModel = remember {
        CourseViewModel(repository = CourseRepository())
    }
    val state by viewModel.state.collectAsState()

    CourseListScreen(
        courseListState = state,
        onItemClick = { courseKey -> viewModel.toggleExpanded(courseKey) }
    )
}


@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun CourseListScreenLightPreview() {
    val viewModel = remember {
        CourseViewModel(repository = CourseRepository())
    }
    val state by viewModel.state.collectAsState()
    AcadamicCourseTheme(darkTheme = false) {
        CourseListScreen(
            courseListState = state,
            onItemClick = { courseKey -> viewModel.toggleExpanded(courseKey) }
        )
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CourseListScreenDarkPreview() {
    val viewModel = remember {
        CourseViewModel(repository = CourseRepository())
    }
    val state by viewModel.state.collectAsState()
    AcadamicCourseTheme(darkTheme = true) {
        CourseListScreen(
            courseListState = state,
            onItemClick = { courseKey -> viewModel.toggleExpanded(courseKey) }
        )
    }
}