package com.faircorp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class RoomsActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}