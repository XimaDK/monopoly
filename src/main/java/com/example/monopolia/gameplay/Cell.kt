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


data class Cell(val name: String, val imageResId: Int, val price: String)

class CellView(context: Context) {

    private val rootView: View = LayoutInflater.from(context).inflate(R.layout.cell_view, null)
    private val imageView: ImageView = rootView.findViewById(R.id.cell_image)
    private val textView: TextView = rootView.findViewById(R.id.cell_text)
    private val chipStackLayout: LinearLayout = rootView.findViewById(R.id.chipStackLayout)


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
    val chipImageView = ImageView(context)
    chipImageView.layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    chipImageView.setImageResource(R.drawable.chip)

    val shapeDrawable = ContextCompat.getDrawable(context, R.drawable.chip) as GradientDrawable

    shapeDrawable.setColor(getPlayerColor(playerIndex, context))

    shapeDrawable.setStroke(8, ContextCompat.getColor(context, android.R.color.black))

    chipImageView.background = shapeDrawable

    val chipContainer = rootView.findViewById<LinearLayout>(R.id.chipStackLayout)

    if (visible) {
        val frameLayout = FrameLayout(context)
        frameLayout.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        frameLayout.addView(chipImageView)

        chipContainer.addView(frameLayout)
        chipContainer.gravity = Gravity.CENTER
    } else {
        chipContainer.removeAllViews()
    }
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
    }


}