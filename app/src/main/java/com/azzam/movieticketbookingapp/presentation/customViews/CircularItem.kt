package com.azzam.movieticketbookingapp.presentation.customViews

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.azzam.movieticketbookingapp.R
import com.azzam.movieticketbookingapp.databinding.LayoutCircularItemBinding

class CircularItem(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    lateinit var binding: LayoutCircularItemBinding

    init {
        binding = LayoutCircularItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setUp(@DrawableRes image: Int, background: Bitmap) {
        val strokeUnSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR, intArrayOf(
                ContextCompat.getColor(context, R.color.bright_turquoise),
                ContextCompat.getColor(context, R.color.bright_turquoise_1)
            )
        )

        binding.stroke.setImageDrawable(strokeUnSelected)
        binding.iv.setImageResource(image)
        binding.bg.setImageBitmap(background)
    }

    fun setUp(@DrawableRes image: Int, @ColorRes background: Int) {
        val strokeUnSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR, intArrayOf(
                ContextCompat.getColor(context, R.color.bright_turquoise),
                ContextCompat.getColor(context, R.color.bright_turquoise_1)
            )
        )

        binding.stroke.setImageDrawable(strokeUnSelected)
        binding.iv.setImageResource(image)
        binding.bg.setImageResource(background)
    }
}