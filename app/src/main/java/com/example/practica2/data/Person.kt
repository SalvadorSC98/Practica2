package com.example.practica2.data

import java.io.Serializable

open class Person (
    open val name : String,
    open val surname : String
) : Serializable