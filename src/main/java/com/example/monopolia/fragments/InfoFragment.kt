package com.example.monopolia.fragments

import android.content.res.Resources
import android.media.AsyncPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.monopolia.R
import com.example.monopolia.gameplay.Cell
import com.example.monopolia.gameplay.EventFieldHandler
import com.example.monopolia.gameplay.GameFieldManager
import com.example.monopolia.gameplay.MonopolyManager
import com.example.monopolia.gameplay.Player

class InfoFragment : Fragment() {

    private lateinit var btnSwitchPlayer: Button
    private lateinit var btnRoll: Button
    private lateinit var btnBuy: Button
    private var diceResult = 0
    private lateinit var diceImage: ImageView
    private lateinit var gameFieldManager: GameFieldManager
    private lateinit var showBalanceText : TextView
    private lateinit var payButton: Button
    private lateinit var eventFieldHandler : EventFieldHandler


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_info, container, false)

        btnBuy = view.findViewById(R.id.buy)
        btnRoll = view.findViewById(R.id.roll)
        diceImage = view.findViewById(R.id.diceImage)
        btnSwitchPlayer = view.findViewById(R.id.switchPlayer)
        showBalanceText = view.findViewById(R.id.infoBalance)
        payButton = view.findViewById(R.id.payButton)
        eventFieldHandler = EventFieldHandler(requireContext())


        btnRoll.setOnClickListener {
            printRollDice()
            gameFieldManager.moveChip(diceResult)
            btnSwitchPlayer.visibility = View.VISIBLE
            btnRoll.isEnabled = false
            checkAndShowPayButton()
        }

        btnBuy.setOnClickListener {
            val currentCell = gameFieldManager.getCurrentCell()
            currentCell?.let {
                buyBrand(it)
            }
        }

        btnSwitchPlayer.setOnClickListener {
            gameFieldManager.switchPlayer()
            btnSwitchPlayer.visibility = View.INVISIBLE
            btnRoll.isEnabled = true
        }


        return view
    }

    private fun checkAndShowPayButton() {
        val currentCell = gameFieldManager.getCurrentCell()
        val currentOwner = currentCell?.getCurrentOwner()

        if (currentOwner != -1 && currentOwner != gameFieldManager.getCurrentPlayerIndex()) {
            payButton.visibility = View.VISIBLE

            payButton.setOnClickListener {
                handlePayButtonClick()
            }
        } else {
            payButton.visibility = View.GONE
        }
    }

    private fun handlePayButtonClick() {
        val currentCell = gameFieldManager.getCurrentCell()
        val currentPlayer = gameFieldManager.getPlayer(gameFieldManager.getCurrentPlayerIndex())
        val ownerPlayer = gameFieldManager.getPlayer(currentCell?.getCurrentOwner() ?: -1)
        val priceToPay = currentCell?.price?.toDoubleOrNull() ?: 0.0

        if (currentPlayer.getBalance() >= priceToPay) {
            currentPlayer.pay(ownerPlayer, priceToPay.toInt()/2)
            //потом убрать
            Toast.makeText(requireContext(), "чек", Toast.LENGTH_SHORT).show()

            payButton.visibility = View.GONE
        } else {
            Toast.makeText(requireContext(), "Недостаточно средств для оплаты.", Toast.LENGTH_SHORT).show()
        }
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

    private fun buyBrand(cell: Cell) {
        val playerIndex = gameFieldManager.getCurrentPlayerIndex()
        val cellView = gameFieldManager.getCellView(cell)
        val currentPlayer = gameFieldManager.getPlayer(playerIndex)

        if (cell.getCurrentOwner() == -1 && currentPlayer.purchaseBrand(cell)) {
            cell.owners.add(playerIndex)
            if (cellView != null) {
                gameFieldManager.updateCellBackgroundColor(playerIndex, cellView)
                getBalance(currentPlayer)
            }
        } else {
            Toast.makeText(requireContext(), "Этот бренд нельзя купить", Toast.LENGTH_SHORT).show()

        }
    }

    private fun getBalance(player: Player) {
        val balance = player.getBalance()
        showBalanceText.text = balance.toString()
    }
}
