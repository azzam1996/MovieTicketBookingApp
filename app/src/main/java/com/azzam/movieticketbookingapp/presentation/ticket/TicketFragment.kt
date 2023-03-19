package com.azzam.movieticketbookingapp.presentation.ticket

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.helper.widget.Carousel
import coil.load
import com.azzam.movieticketbookingapp.R
import com.azzam.movieticketbookingapp.databinding.FragmentTicketBinding
import com.azzam.movieticketbookingapp.databinding.ItemTicketBinding
import com.azzam.movieticketbookingapp.presentation.MainActivity
import jp.wasabeef.blurry.Blurry

class TicketFragment : Fragment() {

    var binding: FragmentTicketBinding? = null
    val dots = mutableListOf<ImageView>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTicketBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbarItems()
        initAdapter()
        disableScrollViewWhenCarouselIsTapped()
        (activity as MainActivity).showNavBar()
    }

    private fun initToolbarItems() {
        binding?.toolbarItem1?.setUp(R.drawable.ic_back, R.color.persian_indigo)
        binding?.toolbarItem1?.visibility = View.VISIBLE

        binding?.toolbarItem2?.setUp(R.drawable.ic_more, R.color.persian_indigo)
        binding?.toolbarItem2?.visibility = View.VISIBLE
    }

    private fun initAdapter() {
        binding?.carouselTicket?.setAdapter(object : Carousel.Adapter {
            override fun count(): Int {
                return 5
            }

            override fun populate(view: View, index: Int) {
                Log.v("populate", index.toString())
                val ticketBinding = ItemTicketBinding.bind(view)
                if (index % 2 == 1) {
                    ticketBinding.ivPoster.load(R.drawable.ic_poster_2) {
                        allowHardware(false)
                    }
                } else {
                    ticketBinding.ivPoster.load(R.drawable.ic_poster) {
                        allowHardware(false)
                    }
                }

                if (index == binding?.carouselTicket?.currentIndex) {
                    ticketBinding.bottomCard.visibility = View.VISIBLE
                    ticketBinding.root.post {
                        Blurry.with(requireContext())
                            .radius(5)
                            .sampling(50)
                            .capture(ticketBinding?.ivPoster)
                            .getAsync {
                                val croppedBitmap = Bitmap.createBitmap(
                                    it,
                                    (it?.width?.minus(50)) ?: 0,
                                    (it?.height?.minus(50)) ?: 0,
                                    50,
                                    50
                                )

                                ticketBinding?.ivBlurBg?.load(croppedBitmap) {
                                    allowHardware(false)
                                }
                            }
                    }

                } else {
                    ticketBinding.bottomCard.visibility = View.GONE
                }

                for (i in 0 until count()) {
                    dots[i].setImageResource(R.drawable.non_active_dot)
                }
                binding?.carouselTicket?.currentIndex?.let {
                    dots[it].setImageResource(R.drawable.active_dot)
                }
            }

            override fun onNewItem(index: Int) {
                // called when an item is set
            }
        })

        dots.clear()
        val numOfImages = binding?.carouselTicket?.count ?: 0
        for (i in 0..numOfImages) {
            val iv = ImageView(requireContext())
            iv.maxWidth = 4
            iv.maxHeight = 4
            dots.add(iv)
        }

        for (i in 0 until numOfImages) {
            dots[i].setImageResource(R.drawable.non_active_dot)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(4, 0, 4, 0)
            dots[0].setImageResource(R.drawable.active_dot)
            binding?.SliderDots?.addView(dots[i], params)
        }
    }

    private fun disableScrollViewWhenCarouselIsTapped() {
        binding?.ticket2?.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                when (p1?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        binding?.sv?.requestDisallowInterceptTouchEvent(true);
                        return false;
                    }
                    MotionEvent.ACTION_UP -> {
                        binding?.sv?.requestDisallowInterceptTouchEvent(true);
                        return false;
                    }
                    MotionEvent.ACTION_MOVE -> {
                        binding?.sv?.requestDisallowInterceptTouchEvent(true);
                        return false;
                    }
                }
                return true
            }
        })
    }

    override fun onResume() {
        super.onResume()
        try {
            binding?.carouselTicket?.refresh()
        } catch (e: Exception) {
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}