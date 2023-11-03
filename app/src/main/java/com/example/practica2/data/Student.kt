package com.example.practica2.data

import java.io.Serializable

data class Student (
    override val name : String,
    override val surname : String,
    val email : String,
    val center : String,
    val city : String,
    val photo : Int,
    val tutor: Tutor
) : Person(name, surname), Serializable