package com.example.practica2

class DataClasses {
    val people = listOf(
        Person("PruebaNombre1", "PruebaApellido1", "prueba1@email.com"),
        Person("PruebaNombre2", "PruebaApellido2", "prueba2@email.com")
    )

    val students = listOf(
        Student("PruebaCentro1", "PruebaCiudad1", R.drawable.imagen1),
        Student("PruebaCentro2", "PruebaCiudad2", R.drawable.imagen2)
    )

    val tutors = listOf(
        Tutor("PruebaNombreTutor1", "PruebaApellidosTutor1"),
        Tutor("PruebaNombreTutor2", "PruebaApellidosTutor2")
    )

    val combinedData = CombinedData(people, students, tutors)
}