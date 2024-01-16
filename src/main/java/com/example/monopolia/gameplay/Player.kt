package com.example.monopolia.gameplay

class Player(val id : Int) {
    private var balance = 5000
    private val purchasedBrands: MutableList<Cell> = mutableListOf()

    fun getBalance(): Int{
        return balance
    }

    fun setBalance(balance : Int){
        this.balance = balance
    }

    fun purchaseBrand(brand: Cell): Boolean {
        val brandPrice = brand.price.toIntOrNull()

        if (brandPrice != null && balance >= brandPrice) {
            balance -= brandPrice
            purchasedBrands.add(brand)
            return true
        }

        return false
    }

}
