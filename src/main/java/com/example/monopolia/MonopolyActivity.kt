package com.example.monopolia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.example.monopolia.fragments.InfoFragment
import com.example.monopolia.gameplay.GameFieldManager

class MonopolyActivity : AppCompatActivity() {

    private lateinit var gameFieldManager: GameFieldManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monopoly)
        val containerView = findViewById<ViewGroup>(R.id.gameFieldContainer) //
        gameFieldManager = GameFieldManager(this, containerView)
        gameFieldManager.populateField()


        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val infoFragment = InfoFragment()

        infoFragment.setGameFieldManager(gameFieldManager)

        transaction.replace(R.id.fragment_info, infoFragment)
        transaction.commit()

    }



}
