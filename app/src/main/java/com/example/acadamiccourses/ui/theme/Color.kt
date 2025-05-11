package com.example.acadamiccourses.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.MaterialTheme

object CategoryColors {
    val Business = Color(0xFF4CAF50)
    val Design = Color(0xFF9C27B0)
    val Accounting = Color(0xFFF44336)
    val Software = Color(0xFF2196F3)
    val Finance = Color(0xFFFF9800)

    fun getColorForCategory(category: String, defaultColor: Color = Color(0xFF6200EE)): Color {
        return when (category.lowercase()) {
            "business" -> Business
            "design" -> Design
            "accounting" -> Accounting
            "software" -> Software
            "finance" -> Finance
            else -> defaultColor
        }
    }

    @Composable
    fun rememberCategoryColor(category: String): Color {
        val defaultColor = MaterialTheme.colorScheme.primary
        return remember(category, defaultColor) {
            getColorForCategory(category, defaultColor)
        }
    }
}