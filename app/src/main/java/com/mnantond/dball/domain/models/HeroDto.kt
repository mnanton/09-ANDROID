package com.mnantond.dball.domain.models

data class HeroDto (
    val id: String,
    val photo: String,
    var favourite: Boolean,
    val name: String,
    val description: String
)