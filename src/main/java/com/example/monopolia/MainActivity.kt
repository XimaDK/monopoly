package com.example.monopolia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnHost : Button = findViewById(R.id.btnForNewGame)
        btnHost.setOnClickListener {
            val monopolyActivity = Intent(this, MonopolyActivity::class.java)
            startActivity(monopolyActivity)
        }
    }
}