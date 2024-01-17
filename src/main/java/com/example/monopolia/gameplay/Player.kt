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

        if (brand.getCurrentOwner() == -1 && brandPrice != null && balance >= brandPrice) {
            balance -= brandPrice
            purchasedBrands.add(brand)
            brand.owners.add(id)
            return true
        }

        return false
    }

    fun pay(receiver: Player, amount: Int) {
        if (balance >= amount) {
            balance -= amount
            receiver.receivePayment(amount)
        } else {

        }
    }

    fun payTax(amount: Int) {
        balance -= amount
    }
    private fun receivePayment(amount: Int) {
        balance += amount
    }

}
