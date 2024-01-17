package com.example.monopolia.gameplay

import android.content.Context
import android.widget.Toast

class EventFieldHandler(private val context: Context) {

    fun handleEvent(cell : Cell, player: Player) {
        when (cell.eventType) {
            EventType.TAX -> handleTaxEvent(player)
//            EventType.JAIL -> handleEvent()
            else -> {

            }
        }
    }

    private fun handleTaxEvent(player: Player) {
        val taxAmount = 500
        if (player.getBalance() >= taxAmount) {
            player.payTax(taxAmount)


            Toast.makeText(
                context,
                "Оплачено налога: $taxAmount",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                context,
                "Недостаточно средств для оплаты налога.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}