package com.example.tensorhw8.model

data class Department(override val id: Int, val title: String) : ListItem() {
    companion object {
        fun getMockDepartments() = listOf(
            create(0),
            create(1),
            create(2)
        )

        fun create(id: Int) : Department {
            val title = when (id) {
                0 -> "Fitness"
                1 -> "Action"
                2 -> "Comedy"
                3 -> "Adventures"
                4 -> "Game of Thrones"
                5 -> "Bad Girls"
                6 -> "Avengers"
                else -> "Unknown"
            }
            return Department(id, title)
        }
    }
}