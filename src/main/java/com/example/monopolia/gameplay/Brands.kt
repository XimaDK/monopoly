package com.example.monopolia.gameplay

data class Cell(val name: String, val price: Int){
    private var nextCell : Cell? = null
}
