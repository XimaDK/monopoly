package com.example.monopolia.gameplay

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.monopolia.R


data class Cell(val name : String, val imageResId: Int, val price: String)

class CellView(context: Context) {

    private val rootView: View = LayoutInflater.from(context).inflate(R.layout.cell_view, null)
    private val imageView: ImageView = rootView.findViewById(R.id.cell_image)
    private val textView: TextView = rootView.findViewById(R.id.cell_text)


    fun setDirection(direction: Int) {
        when (direction) {
            2,4 -> {
                textView.rotation = 90f
                textView.layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                ).apply {
                    gravity = Gravity.START
                }
            }

        }
    }

    fun setCellImage(resourceId: Int) {
        imageView.setImageResource(resourceId)
    }

    fun setCellText(text: String?) {
        textView.text = text
    }

    fun getView(): View {
        return rootView
    }

    fun setChipVisible(visible: Boolean, playerIndex: Int, context: Context) {
        val chipImageView = rootView.findViewById<ImageView>(R.id.game_piece1)
        if (visible){
            chipImageView.visibility = View.VISIBLE
            chipImageView.setColorFilter(getPlayerColor(playerIndex, context))
        }else{
            chipImageView.visibility = View.INVISIBLE
        }
    }

    private fun getPlayerColor(playerIndex: Int, context : Context): Int {
        // Choose different colors for each player
        return when (playerIndex) {
            0 -> ContextCompat.getColor(context, R.color.player1_color)
            1 -> ContextCompat.getColor(context, R.color.player2_color)
            2 -> ContextCompat.getColor(context, R.color.player3_color)
            else -> ContextCompat.getColor(context, R.color.default_player_color)
        }
    }


}