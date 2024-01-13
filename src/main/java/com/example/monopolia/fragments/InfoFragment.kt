package com.example.monopolia.fragments

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.monopolia.R
import com.example.monopolia.gameplay.Cell
import com.example.monopolia.gameplay.GameFieldManager
import com.example.monopolia.gameplay.MonopolyManager

class InfoFragment : Fragment() {

    private lateinit var btnRoll: Button
    private var diceResult = 0
    private lateinit var diceImage: ImageView
    private lateinit var gameFieldManager: GameFieldManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        btnRoll = view.findViewById(R.id.roll)
        diceImage = view.findViewById(R.id.diceImage)

        btnRoll.setOnClickListener {
            printRollDice()
            gameFieldManager.moveChip(diceResult)
            gameFieldManager.switchPlayer()
        }



        return view
    }

    private fun printRollDice() {
        diceResult = MonopolyManager.rollDice()
        val drawableId = when (diceResult) {
            1 -> R.drawable.dice1
            2 -> R.drawable.dice2
            3 -> R.drawable.dice3
            4 -> R.drawable.dice4
            5 -> R.drawable.dice5
            6 -> R.drawable.dice6
            else -> null
        }

        diceImage.setImageResource(drawableId as Int)
    }

    fun setGameFieldManager(manager: GameFieldManager) {
        gameFieldManager = manager
    }

    fun buyBrand(cell : Cell){

    }
}
