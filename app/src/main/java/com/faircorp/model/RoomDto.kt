package com.faircorp.model

data class RoomDto(val id: Long,
                   val name: String,
                   val floor: String,
                   val currentTemperature: Double?,
                   val targetTemperature: Double?)