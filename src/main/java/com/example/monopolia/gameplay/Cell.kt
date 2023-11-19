package com.example.monopolia.gameplay

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.monopolia.R


data class Cell(val name : String, val imageResId: Int, val price: String)

class CellView(context: Context) {

    private val rootView: View = LayoutInflater.from(context).inflate(R.layout.cell_view, null)
    private val imageView: ImageView = rootView.findViewById(R.id.cell_image)
    private val textView: TextView = rootView.findViewById(R.id.cell_text)


    fun setCellImage(resourceId: Int) {
        imageView.setImageResource(resourceId)
    }

    fun setCellText(text: String?) {
        textView.text = text
    }

    fun getView(): View {
        return rootView
    }
}