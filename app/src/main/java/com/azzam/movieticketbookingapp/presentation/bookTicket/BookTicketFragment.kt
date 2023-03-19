package com.azzam.movieticketbookingapp.presentation.bookTicket

import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.helper.widget.Carousel
import androidx.core.content.ContextCompat
import com.azzam.movieticketbookingapp.R
import com.azzam.movieticketbookingapp.databinding.FragmentBookTicketBinding
import com.azzam.movieticketbookingapp.databinding.ItemMovieDateBinding
import com.azzam.movieticketbookingapp.databinding.ItemMovieTimeBinding
import com.azzam.movieticketbookingapp.presentation.MainActivity
import jp.wasabeef.blurry.Blurry

class BookTicketFragment : Fragment() {

    var binding: FragmentBookTicketBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookTicketBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbarItems()
        initButton()
        initAdapters()
        disableScrollViewWhenCarouselIsTapped()

        (activity as MainActivity).hideNavBar()
    }

    private fun initAdapters() {
        val strokeUnSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.bright_turquoise),
                ContextCompat.getColor(requireContext(), R.color.bright_turquoise_1)
            )
        )
        val strokeSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.hot_pink),
                ContextCompat.getColor(requireContext(), R.color.hot_pink_2)
            )
        )

        val bgSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.jazzberry_jam),
                ContextCompat.getColor(requireContext(), R.color.persian_indigo)
            )
        )
        val bgUnSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.persian_indigo),
                ContextCompat.getColor(requireContext(), R.color.charade)
            )
        )

        strokeUnSelected.cornerRadius = resources.getDimension(R.dimen.base10)
        strokeSelected.cornerRadius = resources.getDimension(R.dimen.base10)
        bgSelected.cornerRadius = resources.getDimension(R.dimen.base10)
        bgUnSelected.cornerRadius = resources.getDimension(R.dimen.base10)

        binding?.carousel?.setAdapter(object : Carousel.Adapter {
            override fun count(): Int {
                return 20
            }

            override fun populate(view: View, index: Int) {
                val itemBinding = ItemMovieDateBinding.bind(view)
                itemBinding.tvDayOfMonth.text = index.toString()
                itemBinding.tvDayOfWeek.text = getDayOfWeek(index)
                if (index == (binding?.carousel?.currentIndex ?: 0)) {
                    itemBinding.root.background = strokeSelected
                    itemBinding.clMain.background = bgSelected
                } else {
                    itemBinding.root.background = strokeUnSelected
                    itemBinding.clMain.background = bgUnSelected
                }
            }

            override fun onNewItem(index: Int) {
                // called when an item is set
            }
        })

        binding?.carouselTime?.setAdapter(object : Carousel.Adapter {
            override fun count(): Int {
                return 20
            }

            override fun populate(view: View, index: Int) {
                val itemBinding = ItemMovieTimeBinding.bind(view)
                itemBinding.tvTime.text = "$index:00"
            }

            override fun onNewItem(index: Int) {
                // called when an item is set
            }
        })
    }

    private fun initButton() {

        val strokeUnSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.bright_turquoise),
                ContextCompat.getColor(requireContext(), R.color.bright_turquoise_1)
            )
        )
        val strokeSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.hot_pink),
                ContextCompat.getColor(requireContext(), R.color.hot_pink_2)
            )
        )

        val bgSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.jazzberry_jam),
                ContextCompat.getColor(requireContext(), R.color.persian_indigo)
            )
        )
        val bgUnSelected = GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.persian_indigo),
                ContextCompat.getColor(requireContext(), R.color.charade)
            )
        )

        strokeUnSelected.cornerRadius = resources.getDimension(R.dimen.base20)
        strokeSelected.cornerRadius = resources.getDimension(R.dimen.base20)
        bgSelected.cornerRadius = resources.getDimension(R.dimen.base20)
        bgUnSelected.cornerRadius = resources.getDimension(R.dimen.base20)

        binding?.btnBook?.background = strokeSelected
        binding?.clButtonMain?.background = bgSelected
    }

    private fun initToolbarItems() {
        binding?.root?.post {
            Blurry.with(requireContext())
                .radius(5)
                .sampling(50)
                .capture(binding?.ivPoster)
                .getAsync {
                    val croppedBitmap = Bitmap.createBitmap(
                        it,
                        0,
                        0,
                        20,
                        20
                    )
                    binding?.toolbarItem1?.setUp(R.drawable.ic_back, croppedBitmap)
                    binding?.toolbarItem1?.visibility = View.VISIBLE

                    val croppedBitmap1 = Bitmap.createBitmap(
                        it,
                        it.width - 20,
                        0,
                        20,
                        20
                    )
                    binding?.toolbarItem2?.setUp(R.drawable.ic_more, croppedBitmap1)
                    binding?.toolbarItem1?.visibility = View.VISIBLE
                }
        }
    }

    private fun disableScrollViewWhenCarouselIsTapped() {
        binding?.layoutDate?.setOnTouchListener(object : View.OnTouchListener {
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
        binding?.layoutTime?.setOnTouchListener(object : View.OnTouchListener {
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

    private fun getDayOfWeek(index: Int): String {
        return when (index % 7) {
            0 -> "Sat"
            1 -> "Sun"
            2 -> "Mon"
            3 -> "Tue"
            4 -> "Wed"
            5 -> "Thr"
            6 -> "Fri"
            else -> {
                "Sat"
            }
        }
    }


    override fun onResume() {
        super.onResume()
        try {
            binding?.carousel?.refresh()
            binding?.carouselTime?.refresh()
        } catch (e: Exception) {
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}