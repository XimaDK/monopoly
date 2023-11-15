package com.example.monopolia.gameplay

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.monopolia.R
import com.example.monopolia.interfaces.MoveGamePieceListener

class GameFieldManager(private val context: Context, private val containerView: ViewGroup) {

    private val cellsList: List<Cell> = createCellList()

    private fun createCellList(): List<Cell> {
        return listOf(
            Cell("startfield", R.drawable.startfield, ""),
            Cell("adidas", R.drawable.adidas, "$100"),
            Cell("tax1", R.drawable.tax1, ""),
            Cell("nike", R.drawable.nike, "200"),
            Cell("question1", R.drawable.question1, ""),
            Cell("audi", R.drawable.audi, "1000"),
            Cell("vk", R.drawable.vk, "400"),
            Cell("telegram", R.drawable.telegram, "500"),
            Cell("whatsApp", R.drawable.whatsapp, "600"),
            Cell("bus", R.drawable.bus, ""),
            Cell("pepsi", R.drawable.pepsi, "700"),
            Cell("mercedes", R.drawable.mercedes, "1000"),
            Cell("nestle", R.drawable.nestle, "800"),
            Cell("jail", R.drawable.jail, ""),
            Cell("steam", R.drawable.steam, "900"),
            Cell("tax2", R.drawable.tax2, ""),
            Cell("epicgames", R.drawable.epicgames, "1000"),
            Cell("gogcom", R.drawable.gogcom, "1100"),
            Cell("ford", R.drawable.ford, "1000"),
            Cell("alfabank", R.drawable.alfabank, "1200"),
            Cell("sber", R.drawable.sber, "1300"),
            Cell("question2", R.drawable.question2, ""),
            Cell("vtb", R.drawable.vtb, "1400"),
            Cell("handcuffs", R.drawable.handcuffs, "handcuffs"),
            Cell("nokia", R.drawable.nokia, "1500"),
            Cell("subaru", R.drawable.subaru, "1000"),
            Cell("apple", R.drawable.apple, "1600")
        )
    }

    fun populateField() {
        val topRowContainer = containerView.findViewById<ConstraintLayout>(R.id.topRowContainer)
        val topRowCells = cellsList.subList(0, 10)
        addCellsToContainer(topRowContainer, topRowCells)

        val leftColumnContainer = containerView.findViewById<LinearLayout>(R.id.leftColumnContainer)
        val leftColumnCells = cellsList.subList(10, 13)
        addCellsToContainer(leftColumnContainer, leftColumnCells)

        val rightColumnContainer = containerView.findViewById<LinearLayout>(R.id.rightColumnContainer)
        val rightColumnCells = cellsList.subList(13, 23)
        addCellsToContainer(rightColumnContainer, rightColumnCells)

        val bottomRowContainer = containerView.findViewById<ConstraintLayout>(R.id.bottomRowContainer)
        val bottomRowCells = cellsList.subList(24, cellsList.size)
        addCellsToContainer(bottomRowContainer, bottomRowCells)
    }

    private fun addCellsToContainer(container: ViewGroup, cellList: List<Cell>) {
        val context = container.context
        val constraintSet = ConstraintSet()

        var prevCellView: View? = null

        for (cell in cellList) {
            val cellView = CellView(context)
            cellView.setCellImage(cell.imageResId)
            cellView.setCellText(cell.price)

            val uniqueId = View.generateViewId()
            cellView.id = uniqueId

            val layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            container.addView(cellView, layoutParams)

            if (container is ConstraintLayout) {
                constraintSet.clone(container)

                if (prevCellView != null) {
                    constraintSet.connect(
                        cellView.id,
                        ConstraintSet.START,
                        prevCellView.id,
                        ConstraintSet.END
                    )
                    constraintSet.setHorizontalBias(cellView.id, 0.5f)
                } else {
                    constraintSet.connect(
                        cellView.id,
                        ConstraintSet.START,
                        ConstraintSet.PARENT_ID,
                        ConstraintSet.START
                    )
                }

                constraintSet.applyTo(container)
            } else if (container is LinearLayout) {
                if (prevCellView != null) {
                    val linearLayoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    linearLayoutParams.marginStart = 10
                    cellView.layoutParams = linearLayoutParams
                }
            }

            prevCellView = cellView
        }
    }
}