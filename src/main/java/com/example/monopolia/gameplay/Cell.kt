package com.example.monopolia.gameplay

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.monopolia.R


data class Cell(val name : String, val imageResId: Int, val price: String)

class CellView(context: Context) {

    private val rootView: View = LayoutInflater.from(context).inflate(R.layout.cell_view, null)
    private val imageView: ImageView = rootView.findViewById(R.id.cell_image)
    private val textView: TextView = rootView.findViewById(R.id.cell_text)
    private val chipStackLayout: LinearLayout = rootView.findViewById(R.id.chipStackLayout)


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

        val chipImageView = ImageView(context)
        chipImageView.setImageResource(R.drawable.chip) // Use your chip image resource
        chipImageView.setColorFilter(getPlayerColor(playerIndex, context))

        if (visible) {
            chipStackLayout.addView(chipImageView)
        } else {
            chipStackLayout.removeView(chipImageView)

        }
    }


    private fun getPlayerColor(playerIndex: Int, context : Context): Int {
        return when (playerIndex) {
            0 -> ContextCompat.getColor(context, R.color.player1_color)
            1 -> ContextCompat.getColor(context, R.color.player2_color)
            2 -> ContextCompat.getColor(context, R.color.player3_color)
            else -> ContextCompat.getColor(context, R.color.default_player_color)
        }
    }


}