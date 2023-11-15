package com.example.monopolia.gameplay

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView



data class Cell(val name : String, val imageResId: Int, val price: String)

class CellView(context: Context) : FrameLayout(context) {
    private var imageView: ImageView? = null
    private var textView: TextView? = null

    init {
        init(context)
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(com.example.monopolia.R.layout.cell_view, this, true)
        imageView = findViewById(com.example.monopolia.R.id.cell_image)
        textView = findViewById(com.example.monopolia.R.id.cell_text)
    }

    fun setCellImage(resourceId: Int) {
        imageView!!.setImageResource(resourceId)
    }

    fun setCellText(text: String?) {
        textView!!.text = text
    }

    fun addChip(chipImageResource: Int) {
        // Логика добавления фишки
    }

    fun removeChip() {
        // Логика удаления фишки
    }
}
