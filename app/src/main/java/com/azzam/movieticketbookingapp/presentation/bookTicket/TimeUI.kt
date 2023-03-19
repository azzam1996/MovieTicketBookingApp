package com.azzam.movieticketbookingapp.presentation.bookTicket

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.azzam.movieticketbookingapp.R
import com.azzam.movieticketbookingapp.databinding.ItemMovieTimeBinding

class TimeUI(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    lateinit var binding: ItemMovieTimeBinding

    init {
        binding = ItemMovieTimeBinding.inflate(LayoutInflater.from(context), this, true)

        val strokeUnSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR, intArrayOf(
                ContextCompat.getColor(context, R.color.bright_turquoise),
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

        strokeUnSelected.cornerRadius = resources.getDimension(R.dimen.base10)
        strokeSelected.cornerRadius = resources.getDimension(R.dimen.base10)
        bgSelected.cornerRadius = resources.getDimension(R.dimen.base10)
        bgUnSelected.cornerRadius = resources.getDimension(R.dimen.base10)



        binding.root.background = strokeUnSelected
        binding.clMain.background = bgUnSelected
    }

    fun setUp(text: String) {
        binding.tvTime.text = text
    }
}