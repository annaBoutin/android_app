package com.faircorp.model

class WindowService {

    companion object {
        // Fake rooms
        val ROOMS: List<RoomDto> = listOf(

        )

        // Fake lights
        val WINDOWS: List<WindowDto> = listOf(

        )
    }

    fun findById(id: Long) = WINDOWS.firstOrNull { it.id == id}

    fun findAll() = WINDOWS.sortedBy { it.name }
}