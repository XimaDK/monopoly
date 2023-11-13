package com.example.monopolia.gameplay

class MonopolyGameManager {

    fun rollDice() : Int{
        return (1..6).random()
    }

    fun getBrandList(): ArrayList<Cell> {
        return arrayListOf(
            Cell("startField", 0), Cell("adidas", 110), Cell("tax1", 100), Cell("nike", 200),
            Cell("question", 200), Cell("audi", 1000), Cell("vk", 400),
            Cell("question2", 0),
            Cell("telegram", 500), Cell("whatsapp", 600),
            Cell("bus", 0), Cell("pepsi", 700),
            Cell("mercedes", 1000), Cell("nestle", 800), Cell("jail", 0),
            Cell("steam", 900), Cell("tax2", 0), Cell("epicGames", 1000),
            Cell("gogcom", 1100), Cell("ford", 1000),
            Cell("alfabank", 1200), Cell("sber", 1300), Cell("question3", 0),
            Cell("vtb", 1400), Cell("handcuffs", 0),
            Cell("nokia", 1500), Cell("subaru", 1500), Cell("apple", 1600)
        )
    }
}