package com.mnantond.dball.domain.models

data class Hero (
    val id: String,
    val photo: String,
    val name: String,
    var HealthState: Int = 100)
    {
    val HealthMax: Int = 100
    }
