package com.azzam.movieticketbookingapp.presentation.customViews


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.toBitmapOrNull
import com.azzam.movieticketbookingapp.R
import com.azzam.movieticketbookingapp.databinding.LayoutBottomNavigationBarBinding
import jp.wasabeef.blurry.Blurry

class CustomBottomNavigationBar(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {

    lateinit var binding: LayoutBottomNavigationBarBinding

    init {
        binding = LayoutBottomNavigationBarBinding.inflate(LayoutInflater.from(context), this, true)

        binding.root.post {
            Blurry.with(context)
                .radius(20)
                .sampling(120)
                .from(Bitmap.createBitmap(BitmapFactory.decodeResource(resources, R.drawable.bg1)))
                .into(binding?.blur)

            val bitmap = binding?.blur?.drawable?.toBitmapOrNull(
                300,
                100,
                Bitmap.Config.ARGB_8888
            )

            bitmap?.let {
                val croppedBitmap = Bitmap.createBitmap(
                    it,
                    0,
                    0,
                    60,
                    100
                )

                val croppedBitmap1 = Bitmap.createBitmap(
                    it,
                    60,
                    0,
                    60,
                    100
                )

                val croppedBitmap2 = Bitmap.createBitmap(
                    it,
                    120,
                    0,
                    60,
                    100
                )

                val croppedBitmap3 = Bitmap.createBitmap(
                    it,
                    180,
                    0,
                    60,
                    100
                )

                val croppedBitmap4 = Bitmap.createBitmap(
                    it,
                    240,
                    0,
                    60,
                    100
                )

                if (resources.getBoolean(R.bool.isRtl)) {
                    binding?.homeSelected?.setUp(R.drawable.ic_home, croppedBitmap4)
                    binding?.locationSelected?.setUp(R.drawable.ic_location, croppedBitmap3)
                    binding?.ticketSelected?.setUp(R.drawable.ic_ticket, croppedBitmap2)
                    binding?.calendarSelected?.setUp(R.drawable.ic_calendar, croppedBitmap1)
                    binding?.profileSelected?.setUp(R.drawable.ic_profile, croppedBitmap)
                } else {
                    binding?.homeSelected?.setUp(R.drawable.ic_home, croppedBitmap)
                    binding?.locationSelected?.setUp(R.drawable.ic_location, croppedBitmap1)
                    binding?.ticketSelected?.setUp(R.drawable.ic_ticket, croppedBitmap2)
                    binding?.calendarSelected?.setUp(R.drawable.ic_calendar, croppedBitmap3)
                    binding?.profileSelected?.setUp(R.drawable.ic_profile, croppedBitmap4)
                }
            }
        }

    }

    fun setClickAction(action: (Int) -> Unit) {
        binding.home.setOnClickListener {
            binding.home.visibility = View.INVISIBLE
            binding.location.visibility = View.VISIBLE
            binding.ticket.visibility = View.VISIBLE
            binding.calendar.visibility = View.VISIBLE
            binding.profile.visibility = View.VISIBLE

            binding.homeSelected.visibility = View.VISIBLE
            binding.locationSelected.visibility = View.INVISIBLE
            binding.ticketSelected.visibility = View.INVISIBLE
            binding.calendarSelected.visibility = View.INVISIBLE
            binding.profileSelected.visibility = View.INVISIBLE

            action.invoke(0)
        }

        binding.location.setOnClickListener {
            binding.home.visibility = View.VISIBLE
            binding.location.visibility = View.INVISIBLE
            binding.ticket.visibility = View.VISIBLE
            binding.calendar.visibility = View.VISIBLE
            binding.profile.visibility = View.VISIBLE

            binding.homeSelected.visibility = View.INVISIBLE
            binding.locationSelected.visibility = View.VISIBLE
            binding.ticketSelected.visibility = View.INVISIBLE
            binding.calendarSelected.visibility = View.INVISIBLE
            binding.profileSelected.visibility = View.INVISIBLE
            action.invoke(1)
        }

        binding.ticket.setOnClickListener {
            binding.home.visibility = View.VISIBLE
            binding.location.visibility = View.VISIBLE
            binding.ticket.visibility = View.INVISIBLE
            binding.calendar.visibility = View.VISIBLE
            binding.profile.visibility = View.VISIBLE

            binding.homeSelected.visibility = View.INVISIBLE
            binding.locationSelected.visibility = View.INVISIBLE
            binding.ticketSelected.visibility = View.VISIBLE
            binding.calendarSelected.visibility = View.INVISIBLE
            binding.profileSelected.visibility = View.INVISIBLE
            action.invoke(2)
        }

        binding.calendar.setOnClickListener {
            binding.home.visibility = View.VISIBLE
            binding.location.visibility = View.VISIBLE
            binding.ticket.visibility = View.VISIBLE
            binding.calendar.visibility = View.INVISIBLE
            binding.profile.visibility = View.VISIBLE

            binding.homeSelected.visibility = View.INVISIBLE
            binding.locationSelected.visibility = View.INVISIBLE
            binding.ticketSelected.visibility = View.INVISIBLE
            binding.calendarSelected.visibility = View.VISIBLE
            binding.profileSelected.visibility = View.INVISIBLE
            action.invoke(3)
        }

        binding.profile.setOnClickListener {
            binding.home.visibility = View.VISIBLE
            binding.location.visibility = View.VISIBLE
            binding.ticket.visibility = View.VISIBLE
            binding.calendar.visibility = View.VISIBLE
            binding.profile.visibility = View.INVISIBLE

            binding.homeSelected.visibility = View.INVISIBLE
            binding.locationSelected.visibility = View.INVISIBLE
            binding.ticketSelected.visibility = View.INVISIBLE
            binding.calendarSelected.visibility = View.INVISIBLE
            binding.profileSelected.visibility = View.VISIBLE
            action.invoke(4)
        }
    }
}