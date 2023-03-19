package com.azzam.movieticketbookingapp.presentation.ticket

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.azzam.movieticketbookingapp.R
import com.azzam.movieticketbookingapp.databinding.ItemTicketBinding

class TicketItem(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    lateinit var binding: ItemTicketBinding

    init {
        binding = ItemTicketBinding.inflate(LayoutInflater.from(context), this, true)

        val strokeUnSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR, intArrayOf(
                ContextCompat.getColor(context, R.color.bright_turquoise),
                ContextCompat.getColor(context, R.color.bright_turquoise_1),
                ContextCompat.getColor(context, R.color.bright_turquoise_1)
            )
        )
        val strokeSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR, intArrayOf(
                ContextCompat.getColor(context, R.color.hot_pink),
                ContextCompat.getColor(context, R.color.hot_pink_2)
            )
        )

        val bgSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR, intArrayOf(
                ContextCompat.getColor(context, R.color.jazzberry_jam),
                ContextCompat.getColor(context, R.color.persian_indigo)
            )
        )
        val bgUnSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR, intArrayOf(
                ContextCompat.getColor(context, R.color.persian_indigo),
                ContextCompat.getColor(context, R.color.charade)
            )
        )

        strokeUnSelected.cornerRadius = resources.getDimension(R.dimen.base40)
        strokeSelected.cornerRadius = resources.getDimension(R.dimen.base20)
        bgSelected.cornerRadius = resources.getDimension(R.dimen.base20)
        bgUnSelected.cornerRadius = resources.getDimension(R.dimen.base20)

        binding.bgImage.background = strokeUnSelected

        binding.root.setBackgroundColor(android.R.color.transparent)
    }

    fun setImage(@DrawableRes image: Int) {
        binding.ivPoster.setImageResource(image)
    }
}