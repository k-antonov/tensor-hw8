package com.example.tensorhw8.model

data class Department(override val id: Int, override var name: String) : ListItem() {
    companion object {
        fun getMockDepartments() = listOf(
            create(100),
            create(101),
            create(102)
        )

        fun create(id: Int) : Department {
            val title = when (id) {
                100 -> "Fitness"
                101 -> "Action"
                102 -> "Comedy"
                103 -> "Adventures"
                104 -> "Game of Thrones"
                105 -> "Bad Girls"
                106 -> "Avengers"
                else -> "Unknown"
            }
            return Department(id, title)
        }
    }
}