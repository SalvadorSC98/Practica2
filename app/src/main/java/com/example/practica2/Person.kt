package com.example.practica2

import java.io.Serializable

data class Person (
    val name : String,
    val surname : String,
    val email : String
) : Serializable

data class Student (
    val center : String,
    val city : String,
    val photo : Int
) : Serializable

data class Tutor (
    val tutorName : String,
    val tutorSurname : String
) : Serializable

data class CombinedData(
    val people: List<Person>,
    val students: List<Student>,
    val tutors: List<Tutor>
): Serializable