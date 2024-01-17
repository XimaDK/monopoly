package com.example.monopolia.gameplay

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.monopolia.R
import com.google.firebase.database.core.view.Event


data class Cell(val name: String, val imageResId: Int, val price: String,
                var owners: MutableList<Int> = mutableListOf(), val eventType : EventType? = null) {
    fun getCurrentOwner(): Int {
        return owners.lastOrNull() ?: -1
    }
}

enum class EventType {
    TAX,
    QUESTION,
    JAIL
}

class CellView(context: Context) {

    private val rootView: View = LayoutInflater.from(context).inflate(R.layout.cell_view, null)
    private val imageView: ImageView = rootView.findViewById(R.id.cell_image)
    private val textView: TextView = rootView.findViewById(R.id.cell_text)
    private val chipStackLayout: LinearLayout = rootView.findViewById(R.id.chipStackLayout)
    private val playerChipMap = HashMap<Int, ImageView>()



    fun setDirection(direction: Int) {
        when (direction) {
            2, 4 -> {
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
        if (visible) {
            if (!playerChipMap.containsKey(playerIndex)) {
                val chipImageView = createChipImageView(context, playerIndex)
                chipStackLayout.addView(chipImageView)
                playerChipMap[playerIndex] = chipImageView
            }
        } else {
            playerChipMap[playerIndex]?.let {
                chipStackLayout.removeView(it)
                playerChipMap.remove(playerIndex)
            }
        }
    }

    private fun createChipImageView(context: Context, playerIndex: Int): ImageView {
        val chipImageView = ImageView(context)
        chipImageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        chipImageView.setImageResource(R.drawable.chip)

        val shapeDrawable = ContextCompat.getDrawable(context, R.drawable.chip) as GradientDrawable
        shapeDrawable.setColor(getPlayerColor(playerIndex, context))
        shapeDrawable.setStroke(8, ContextCompat.getColor(context, android.R.color.black))

        chipImageView.setImageDrawable(shapeDrawable)

        return chipImageView
    }



    fun getPlayerColor(playerIndex: Int, context: Context): Int {
        return when (playerIndex) {
            0 -> ContextCompat.getColor(context, R.color.player1_color)
            1 -> ContextCompat.getColor(context, R.color.player2_color)
            2 -> ContextCompat.getColor(context, R.color.player3_color)
            else -> ContextCompat.getColor(context, R.color.default_player_color)
        }
    }

    fun updateCellBackgroundColor(color: Int) {
        rootView.setBackgroundColor(color)
        val textView = rootView.findViewById<TextView>(R.id.cell_text)
        val currentPrice = textView.text.toString().toDoubleOrNull() ?: 0.0
        textView.text = (currentPrice / 2).toString()
    }



}