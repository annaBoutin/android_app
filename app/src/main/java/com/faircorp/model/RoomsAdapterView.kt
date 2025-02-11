package com.faircorp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.R

class RoomAdapter (val listener: OnRoomSelectedListener): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name : TextView = view.findViewById(R.id.txt_room_list_name)
        val temp : TextView = view.findViewById(R.id.txt_current_temp)
        val floor : TextView = view.findViewById(R.id.txt_room_floor)
    }

    private val items = mutableListOf<RoomDto>()

    fun update(rooms: List<RoomDto>) {
        items.clear()
        items.addAll(rooms)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_rooms_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = items[position]
        holder.apply {
            name.text = room.name
            if (room.currentTemp.toString() == "null") {
                temp.text = "No Data"
            } else {
                temp.text = room.currentTemp.toString()
            }
            floor.text = room.floor
            itemView.setOnClickListener{ listener.onRoomSelected(room.id)}
        }
    }

    override fun onViewRecycled(holder: RoomViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}