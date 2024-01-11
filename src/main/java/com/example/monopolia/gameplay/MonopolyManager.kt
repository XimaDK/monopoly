package com.example.monopolia.gameplay

class MonopolyManager {
    companion object{
        fun rollDice() : Int{
            return (1..6).random()
        }
    }

}