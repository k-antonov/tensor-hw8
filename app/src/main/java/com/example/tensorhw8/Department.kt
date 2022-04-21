package com.example.tensorhw8

data class Department(val title: String) {
    companion object {
        fun getMockDepartments() = listOf(
            Department("Fitness"),
            Department("Action"),
            Department("Comedy")
        )
    }
}