package com.example.monopolia.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.monopolia.R
import com.example.monopolia.gameplay.Cell
import com.example.monopolia.gameplay.MonopolyGameManager
import com.example.monopolia.interfaces.MoveGamePieceListener

class InfoFragment : Fragment() {

    private lateinit var btnRoll : Button
    private lateinit var diceImage : ImageView
    private var moveGamePieceListener: MoveGamePieceListener? = null
    private var currentPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        btnRoll = view.findViewById(R.id.roll)
        diceImage = view.findViewById(R.id.diceImage)

        btnRoll.setOnClickListener {
            moveGamePiece()
        }

        return view
    }

    private fun moveGamePiece() {
        val gameManager = MonopolyGameManager()
        val diceResult = gameManager.rollDice()
        val drawableId = when (diceResult) {
            1 -> R.drawable.dice1
            2 -> R.drawable.dice2
            3 -> R.drawable.dice3
            4 -> R.drawable.dice4
            5 -> R.drawable.dice5
            6 -> R.drawable.dice6
            else -> null // В случае ошибки.
        }


        diceImage.setImageResource(drawableId as Int)
//        val brandsAfterStart = listOf("startField", "adidas", "tax1", "nike", "question1", "audi", "vk", "question2", "telegram", "whatsapp", "bus", "pepsi", "mercedes", "nestle", "jail", "steam", "tax2", "epicGames", "gogcom", "ford", "alfabank", "sber", "question3", "vtb", "handcuffs", "nokia", "subaru", "apple")
        val brands = gameManager.getBrandList()
        // Вычисляем новую позицию с учетом текущей позиции и результата броска кубика
        currentPosition = (currentPosition + diceResult) % gameManager.getBrandList().size
        val brandToMoveTo = brands[currentPosition].name
        moveGamePieceListener?.moveGamePieceToBrand(brandToMoveTo)
    }


    fun setMoveGamePieceListener(listener: MoveGamePieceListener) {
        this.moveGamePieceListener = listener
    }

}
