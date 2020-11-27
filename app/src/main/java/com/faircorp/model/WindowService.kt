package com.faircorp.model

class WindowService {

    companion object {
        // Fake rooms
        val ROOMS: List<RoomDto> = listOf(
            RoomDto(1, "Room EF 6.10", 18.2, 20.0),
            RoomDto(2, "Hall", 18.2, 18.0),
            RoomDto(3, "Room EF 7.10", 21.2, 20.0)
        )

        // Fake lights
        val WINDOWS: List<WindowDto> = listOf(
            WindowDto(1, "Entry Window", "test",0, Status.CLOSED),
            WindowDto(2, "Back Window", "test", 0, Status.CLOSED),
            WindowDto(3, "Sliding door", "test", 0, Status.OPEN),
            WindowDto(4, "Window 1", "test", 0, Status.CLOSED),
            WindowDto(5, "Window 2", "test", 0, Status.CLOSED)
        )
    }

    fun findById(id: Long) = WINDOWS.firstOrNull { it.id == id}

    fun findAll() = WINDOWS.sortedBy { it.name }
}