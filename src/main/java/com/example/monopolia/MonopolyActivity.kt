package com.example.monopolia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.monopolia.fragments.InfoFragment
import com.example.monopolia.gameplay.Cell
import com.example.monopolia.gameplay.GameFieldManager
import com.example.monopolia.interfaces.MoveGamePieceListener

class MonopolyActivity : AppCompatActivity() {

    private lateinit var infoFragment: InfoFragment
    private lateinit var gameFieldManager: GameFieldManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monopoly)
        val containerView = findViewById<ViewGroup>(R.id.gameFieldContainer) // Замените на ваш ID
        gameFieldManager = GameFieldManager(this, containerView)

        // Вызываем метод для создания и размещения ячеек
        gameFieldManager.populateField()
    }
}
