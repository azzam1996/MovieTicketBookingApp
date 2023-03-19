package com.azzam.movieticketbookingapp.presentation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import com.azzam.movieticketbookingapp.R
import com.azzam.movieticketbookingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MovieTicketBookingApp);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }

        initBottomNavBarActions()
    }

    private fun initBottomNavBarActions() {
        binding.bottomNavBar.setClickAction { index ->
            when (index) {
                0 -> {
                    binding.container.findNavController().navigate(R.id.homepageFragment)
                }
                2 -> {
                    binding.container.findNavController().navigate(R.id.ticketFragment)
                }
                3 -> {
                    binding.container.findNavController().navigate(R.id.bookTicketFragment)
                }
            }
        }
    }

    fun hideNavBar() {
        binding.bottomNavBar.visibility = View.GONE
    }

    fun showNavBar() {
        binding.bottomNavBar.visibility = View.VISIBLE
    }
}