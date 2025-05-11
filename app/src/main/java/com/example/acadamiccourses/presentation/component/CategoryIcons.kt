package com.example.acadamiccourses.presentation.component

import androidx.compose.runtime.Composable
import com.example.acadamiccourses.R

@Composable
fun rememberCategoryIcon(category: String): Int {
    return when (category.lowercase()) {
        "business" -> R.mipmap.ic_launcher_foreground
        "design" -> R.mipmap.ic_launcher_foreground
        "accounting" -> R.mipmap.ic_launcher_foreground
        "software" -> R.mipmap.ic_launcher_foreground
        "finance" -> R.mipmap.ic_launcher_foreground
        else -> R.mipmap.ic_launcher_foreground
    }
}