package com.example.practica2.data

import java.io.Serializable

data class Tutor (
    override val name : String,
    override val surname : String,
) : Person(name, surname), Serializable