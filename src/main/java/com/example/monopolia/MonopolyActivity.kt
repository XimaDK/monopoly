package com.example.monopolia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.monopolia.fragments.InfoFragment
import com.example.monopolia.gameplay.Cell
import com.example.monopolia.interfaces.MoveGamePieceListener

class MonopolyActivity : AppCompatActivity(), MoveGamePieceListener {

    private lateinit var infoFragment: InfoFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monopoly)

        infoFragment = InfoFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_info, infoFragment)
            .commit()
        infoFragment.setMoveGamePieceListener(this)
    }

    override fun moveGamePieceToBrand(brandName: String) {
        val frameLayoutForBrand = findViewById<View>(getBrandImageViewId(brandName))
        val imageViewForGamePiece = findViewById<ImageView>(R.id.fiche1)

        if (frameLayoutForBrand != null && imageViewForGamePiece != null) {
            val layoutParams = imageViewForGamePiece.layoutParams

            if (layoutParams is LinearLayout.LayoutParams) {

            } else if (layoutParams is ConstraintLayout.LayoutParams) {

                layoutParams.topToTop = frameLayoutForBrand.id
                layoutParams.bottomToBottom = frameLayoutForBrand.id
                layoutParams.startToStart = frameLayoutForBrand.id
                layoutParams.endToEnd = frameLayoutForBrand.id
            }

            imageViewForGamePiece.layoutParams = layoutParams
            imageViewForGamePiece.requestLayout()
        }
    }

    private fun getBrandImageViewId(brandName: String): Int {
        return resources.getIdentifier(brandName, "id", this.packageName)
    }
}