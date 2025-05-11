package com.example.acadamiccourses.data.model


class CourseRepository {

    fun getDummyCourses(): List<Course> {
        return listOf(
            Course("CS101", "Intro to Programming", "Computer Science", "3", "Learn programming basics with Kotlin.", "None"),
            Course("CS102", "Data Structures", "Computer Science", "4", "Covers lists, stacks, queues, trees, and graphs.", "CS101"),
            Course("CS201", "Algorithms", "Computer Science", "4", "Study sorting, searching, dynamic programming, and greedy algorithms.", "CS102"),
            Course("CS301", "Operating Systems", "Computer Science", "3", "Processes, threads, memory management, and file systems.", "CS201"),
            Course("CS303", "Database Systems", "Computer Science", "3", "SQL, relational models, indexing, and transactions.", "CS102"),
            Course("CS305", "Computer Networks", "Computer Science", "3", "Introduction to networking, TCP/IP, routing, and protocols.", "CS201"),
            Course("CS307", "Software Engineering", "Computer Science", "3", "Development life cycle, Agile, testing, and design patterns.", "CS201"),
            Course("CS309", "Web Development", "Computer Science", "3", "HTML, CSS, JavaScript, and modern web frameworks.", "CS101"),
            Course("MATH101", "Calculus I", "Mathematics", "4", "Limits, derivatives, integrals, and applications.", "None"),
            Course("MATH102", "Calculus II", "Mathematics", "4", "Techniques of integration, series, and polar coordinates.", "MATH101"),
            Course("MATH201", "Linear Algebra", "Mathematics", "3", "Matrices, vector spaces, eigenvalues, and systems of equations.", "MATH101"),
            Course("PHYS101", "General Physics I", "Physics", "4", "Mechanics, motion, forces, energy, and momentum.", "MATH101"),
            Course("PHYS102", "General Physics II", "Physics", "4", "Electricity, magnetism, waves, and optics.", "PHYS101"),
            Course("ENG101", "Academic Writing", "English", "3", "Basics of academic writing, essays, and research papers.", "None"),
            Course("HIST101", "World History", "Humanities", "3", "Historical events and civilizations from ancient to modern times.", "None")
        )
    }
}
