package com.example.beevrationapp.database

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EnergyHarvesterData(
    val timestamp: Long,
    val voltage: Float,
    val current: Float
)