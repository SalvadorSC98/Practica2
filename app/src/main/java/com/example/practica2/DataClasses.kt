package com.example.practica2

import com.example.practica2.data.Student
import com.example.practica2.data.Tutor

object DataClasses {

    val tutors = listOf(
        Tutor("NombreTutor1", "ApellidosTutor1"),
        Tutor("NombreTutor2", "ApellidosTutor2"),
    )

    val students = listOf(
        Student("Nombre1", "Apellido1", "prueba1@email.com","Centro1", "Ciudad1", R.drawable.imagen1, tutors[0]),
        Student("Nombre2", "Apellido2", "prueba2@email.com","Centro2", "Ciudad2", R.drawable.imagen2, tutors[1]),
        Student("Carla", "Medina", "cmmedinaotero@gmail.com", "Casa", "Arona", R.drawable.imagen2, tutors[0])
    )
}