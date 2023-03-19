package com.azzam.movieticketbookingapp.presentation.bookTicket

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.azzam.movieticketbookingapp.databinding.ItemMovieDateBinding

class DateUI(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs)  {
    lateinit var binding: ItemMovieDateBinding
    init {
        binding = ItemMovieDateBinding.inflate(LayoutInflater.from(context), this, true)
    }
}