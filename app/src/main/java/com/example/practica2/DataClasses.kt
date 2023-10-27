package com.example.practica2

import com.example.practica2.data.Student
import com.example.practica2.data.Tutor

object DataClasses {

    val tutors = listOf(
        Tutor("PruebaNombreTutor1", "PruebaApellidosTutor1"),
        Tutor("PruebaNombreTutor2", "PruebaApellidosTutor2")
    )

    val students = listOf(
        Student("PruebaNombre1", "PruebaApellido1", "prueba1@email.com","PruebaCentro1", "PruebaCiudad1", R.drawable.imagen1, tutors[0]),
        Student("PruebaNombre2", "PruebaApellido2", "prueba2@email.com","PruebaCentro2", "PruebaCiudad2", R.drawable.imagen2, tutors[1])
    )
}