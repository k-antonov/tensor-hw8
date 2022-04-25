package com.example.tensorhw8.model

data class Employee(
    override val id: Int,
    val fullName: String,
    val photoUrl: String,
    val department: Department
): ListItem() {
    companion object {
        fun getMockEmployees() = listOf(
            Employee(
                0,
                "Dwayne Johnson",
                "https://upload.wikimedia.org/wikipedia/commons/f/f1/Dwayne_Johnson_2%2C_2013.jpg",
                Department.create(id = 0)
            ),
            Employee(
                1,
                "Tom Cruise",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Tom_Cruise_in_Dec_2012_%282_av_3%29-4.jpg/640px-Tom_Cruise_in_Dec_2012_%282_av_3%29-4.jpg",
                Department.create(id = 1)
            ),
            Employee(
                2,
                "Jim Carrey",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Jim_Carrey_2008.jpg/640px-Jim_Carrey_2008.jpg",
                Department.create(id = 2)
            ),
            Employee(
                3,
                "Emily Blunt",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/17/Emily_Blunt_in_2014.jpg/640px-Emily_Blunt_in_2014.jpg",
                Department.create(id = 3)
            ),
            Employee(
                4,
                "Emilia Clarke",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/Emilia_Clarke_Cannes_2018_%28cropped%29.jpg/640px-Emilia_Clarke_Cannes_2018_%28cropped%29.jpg",
                Department.create(id = 4)
            ),
            Employee(
                5,
                "Ryan Reynolds",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/Ryan_reynolds.jpg/640px-Ryan_reynolds.jpg",
                Department.create(id = 2)
            ),
            Employee(
                6,
                "Emma Stone",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Emma_Stone%2C_Justin_Hurwitz%2C_Damien_Chazelle_%2830116702391%29_%28cropped%29.jpg/640px-Emma_Stone%2C_Justin_Hurwitz%2C_Damien_Chazelle_%2830116702391%29_%28cropped%29.jpg",
                Department.create(id = 5)
            ),
            Employee(
                7,
                "Robert John Downey Jr.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Actor_Robert_Downey_Jr._photographed_by_the_California_Department_of_Corrections.jpg/640px-Actor_Robert_Downey_Jr._photographed_by_the_California_Department_of_Corrections.jpg",
                Department.create(id = 6)
            )
        )
    }
}