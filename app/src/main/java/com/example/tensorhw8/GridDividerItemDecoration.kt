package com.example.tensorhw8

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridDividerItemDecoration(
    private val spacing: Int
) : RecyclerView.ItemDecoration() {

    private lateinit var rect: Rect

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        val totalSpanCount = getTotalSpanCount(parent)
        val spanSize = getItemSpanSize(parent, position)

        outRect.top = if (isInTheFirstRow(position, totalSpanCount)) 0 else spacing
        outRect.left = 0
        outRect.right = if (isLastInRow(position, totalSpanCount, spanSize)) 0 else spacing
        outRect.bottom = 0

        rect = outRect
    }

    private fun isInTheFirstRow(position: Int, totalSpanCount: Int) = position < totalSpanCount

    private fun isFirstInRow(position: Int, totalSpanCount: Int, spanSize: Int) =
        if (totalSpanCount != spanSize) {
            position % totalSpanCount == 0
        } else true

    private fun isLastInRow(position: Int, totalSpanCount: Int, spanSize: Int) =
        isFirstInRow(position + 1, totalSpanCount, spanSize)

    private fun getTotalSpanCount(parent: RecyclerView) =
        (parent.layoutManager as? GridLayoutManager)?.spanCount ?: 1

    private fun getItemSpanSize(parent: RecyclerView, position: Int) =
        (parent.layoutManager as? GridLayoutManager)?.spanSizeLookup?.getSpanSize(position) ?: 1
}