package com.faircorp.model

data class RoomDto(val id: Long,
                   val name: String,
                   val floor: String,
                   val currentTemp: Double?,
                   val targetTemp: Double?)