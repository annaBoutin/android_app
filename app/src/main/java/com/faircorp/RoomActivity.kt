package com.faircorp

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.model.ApiServices
import com.faircorp.model.OnWindowSelectedListener
import com.faircorp.model.RoomAdapter
import com.faircorp.model.WindowAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : BasicActivity(), OnWindowSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getLongExtra(ROOM_NAME_PARAM, 0)

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().roomsApiServices.findById(id).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        val room = it.body()
                        if (room != null) {
                            findViewById<TextView>(R.id.txt_room_room_name).text = room.name
                            findViewById<TextView>(R.id.txt_room_room_floor).text = room.floor
                            if (room.currentTemp.toString() == "null") {
                                findViewById<TextView>(R.id.txt_room_room_current_temp).text = "No Data"
                            } else {
                                findViewById<TextView>(R.id.txt_room_room_current_temp).text = room.currentTemp.toString()
                            }
                            if (room.targetTemp.toString() == "null"){
                                findViewById<TextView>(R.id.txt_room_room_target_temp).text = "No Data"
                            } else {
                                findViewById<TextView>(R.id.txt_room_room_target_temp).text = room.targetTemp.toString()
                            }
                        }

                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on windows loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
        val recyclerView = findViewById<RecyclerView>(R.id.list_room_windows)
        val adapter = WindowAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.findByRoomId(id).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        adapter.update(it.body() ?: emptyList())
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Error on windows loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    override fun onWindowSelected(id: Long) {
        val intent = Intent(this, WindowActivity::class.java).putExtra(WINDOW_NAME_PARAM, id)
        startActivity(intent)
    }
}