package com.example.monopolia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.monopolia.connection.ServerService

class MainActivity : AppCompatActivity() {

    private lateinit var btnForNewGame: Button
    private lateinit var btnForJoin: Button
    private lateinit var nickname: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nickname = findViewById(R.id.nickname)
        btnForNewGame = findViewById(R.id.btnForNewGame)
        btnForNewGame.setOnClickListener {
            val monopolyActivity = Intent(this, MonopolyActivity::class.java)
            monopolyActivity.putExtra("nickname", nickname.text)

//            startService(Intent(this, ServerService::class.java))
            startActivity(monopolyActivity)
        }

        btnForJoin = findViewById(R.id.btnForJoin)
        btnForJoin.setOnClickListener {
            val ip = findViewById<EditText>(R.id.ip)
            val monopolyActivity = Intent(this, MonopolyActivity::class.java)
            monopolyActivity.putExtra("nickname", nickname.text)
            monopolyActivity.putExtra("ip", ip.text.toString())
            startActivity(monopolyActivity)
        }
    }
}