package com.orioninc.techclub.acchelloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        Log.d("MainActivity", "onStart")
    }

    override fun onPause() {
        super.onPause()

        Log.d("MainActivity", "onPause")
    }
}