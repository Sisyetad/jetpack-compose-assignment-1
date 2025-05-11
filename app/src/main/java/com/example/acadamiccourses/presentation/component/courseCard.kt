package com.example.acadamiccourses.presentation.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.acadamiccourses.presentation.ui.CourseItemState
import com.example.acadamiccourses.ui.theme.CategoryColors.rememberCategoryColor

@Composable
fun CourseCard(
    state: CourseItemState,
    onCardClick: () -> Unit
) {
    val categoryColor = rememberCategoryColor(state.course.category)
    val categoryIcon = rememberCategoryIcon(state.course.category)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable { onCardClick() }
            .animateContentSize(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        CourseCardBackground(iconResId = categoryIcon, state, categoryColor, onCardClick)
    }
}

@Composable
private fun CourseCardBackground(
    iconResId: Int,
    state: CourseItemState,
    categoryColor: Color,
    onCardClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Image(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .scale(1.5f)
                .graphicsLayer { alpha = 0.2f }
                .clip(RoundedCornerShape(12.dp))
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                        ),
                        startY = 0f,
                        endY = Float.POSITIVE_INFINITY
                    )
                )
        )
        CourseCardContent(
            state = state,
            categoryColor = categoryColor,
            onExpandClick = onCardClick
        )
    }
}

@Composable
private fun CourseCardContent(
    state: CourseItemState,
    categoryColor: Color,
    onExpandClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        CourseHeader(
            category = state.course.category,
            categoryColor = categoryColor,
            isExpanded = state.isExpanded,
            onExpandClick = onExpandClick
        )

        Spacer(modifier = Modifier.height(8.dp))

        CourseTitle(title = state.course.courseTitle)

        Spacer(modifier = Modifier.height(12.dp))

        CourseDetails(
            code = state.course.courseCode,
            credit = state.course.credit
        )

        if (state.isExpanded) {
            ExpandedCourseContent(
                description = state.course.description,
                prerequisite = state.course.prerequest
            )
        }
    }
}

@Composable
private fun CourseHeader(
    category: String,
    categoryColor: Color,
    isExpanded: Boolean,
    onExpandClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CategoryChip(
            category = category,
            color = categoryColor
        )

        Spacer(modifier = Modifier.weight(1f))

        ExpandButton(
            isExpanded = isExpanded,
            onClick = onExpandClick
        )
    }
}

@Composable
private fun CategoryChip(category: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color.copy(alpha = 0.2f))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = category.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Medium
            ),
            color = color
        )
    }
}

@Composable
private fun ExpandButton(isExpanded: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = if (isExpanded) "Collapse" else "Expand",
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
private fun CourseTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        color = MaterialTheme.colorScheme.onSurface,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(end = 8.dp)
    )
}

@Composable
private fun CourseDetails(code: String, credit: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = code,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
            modifier = Modifier.padding(start = 4.dp)
        )

        CreditBadge(credit = credit)
    }
}

@Composable
private fun CreditBadge(credit: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.8f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = "$credit hrs",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.SemiBold
            ),
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
private fun ExpandedCourseContent(description: String, prerequisite: String) {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        DescriptionSection(description = description)
        Spacer(modifier = Modifier.height(12.dp))
        PrerequisiteSection(prerequisite = prerequisite)
    }
}

@Composable
private fun DescriptionSection(description: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        SectionLabel(label = "Description")
        SectionText(text = description)
    }
}

@Composable
private fun PrerequisiteSection(prerequisite: String) {
    Column {
        SectionLabel(label = "Prerequisite")
        SectionText(text = prerequisite)
    }
}

@Composable
private fun SectionLabel(label: String) {
    Text(
        text = label,
        style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Medium
        ),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
private fun SectionText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
    )
}
