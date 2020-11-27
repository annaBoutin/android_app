package com.faircorp


import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.faircorp.model.ApiServices
import com.faircorp.model.WindowService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WindowActivity : BasicActivity() {

    val windowService = WindowService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
        //val window = windowService.findById(id)

        //if (window != null) {
        //    findViewById<TextView>(R.id.txt_window_name_2).text = window.name
        //    findViewById<TextView>(R.id.txt_room_name).text = window.roomName
            //findViewById<TextView>(R.id.txt_window_current_temperature).text = window.roomName.currentTemperature?.toString()
            //findViewById<TextView>(R.id.txt_window_target_temperature).text = window.roomName.targetTemperature?.toString()
        //    findViewById<TextView>(R.id.txt_window_status).text = window.windowStatus.toString()
        //}

        lifecycleScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.findById(id).execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            val window = it.body()
                            if (window != null) {
                                findViewById<TextView>(R.id.txt_window_name_2).text = window.name
                                findViewById<TextView>(R.id.txt_room_name).text = window.roomName
                                findViewById<TextView>(R.id.txt_window_status).text = window.windowStatus.toString()
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