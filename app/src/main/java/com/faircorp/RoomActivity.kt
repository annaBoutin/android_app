package com.faircorp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : BasicActivity() {
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
    }
}