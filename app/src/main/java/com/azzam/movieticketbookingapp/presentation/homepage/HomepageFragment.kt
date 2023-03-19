package com.azzam.movieticketbookingapp.presentation.homepage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.azzam.movieticketbookingapp.R
import com.azzam.movieticketbookingapp.databinding.FragmentHomepageBinding
import com.azzam.movieticketbookingapp.domain.model.Movie
import com.azzam.movieticketbookingapp.domain.model.MovieCategory
import com.azzam.movieticketbookingapp.presentation.MainActivity
import com.azzam.movieticketbookingapp.presentation.homepage.adapter.MovieCategoryAdapter

class HomepageFragment : Fragment() {

    var binding: FragmentHomepageBinding? = null
    private val movieCategoryAdapter = MovieCategoryAdapter()
    private val list = mutableListOf<MovieCategory>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomepageBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).showNavBar()
        initHardCodedList()
        initAdapter()
    }

    private fun initHardCodedList() {
        list.clear()

        val moviesList = mutableListOf<Movie>()
        moviesList.add(Movie(image = R.drawable.movie_1_1))
        moviesList.add(Movie(image = R.drawable.movie_1_2))
        moviesList.add(Movie(image = R.drawable.movie_1_3))
        moviesList.add(Movie(image = R.drawable.movie_1_4))
        moviesList.add(Movie(image = R.drawable.movie_1_5))
        moviesList.add(Movie(image = R.drawable.movie_2_1))

        list.add(
            MovieCategory(
                title = "Now Playing",
                movies = moviesList
            )
        )

        val moviesList1 = mutableListOf<Movie>()
        moviesList1.add(Movie(image = R.drawable.movie_2_1))
        moviesList1.add(Movie(image = R.drawable.movie_2_2))
        moviesList1.add(Movie(image = R.drawable.movie_2_3))
        moviesList1.add(Movie(image = R.drawable.movie_2_4))
        moviesList1.add(Movie(image = R.drawable.movie_2_5))
        moviesList1.add(Movie(image = R.drawable.movie_2_6))

        list.add(
            MovieCategory(
                title = "Coming Soon",
                movies = moviesList1
            )
        )

        val moviesList2 = mutableListOf<Movie>()
        moviesList2.add(Movie(image = R.drawable.movie_3_1))
        moviesList2.add(Movie(image = R.drawable.movie_3_2))
        moviesList2.add(Movie(image = R.drawable.movie_2_5))
        moviesList2.add(Movie(image = R.drawable.movie_2_6))


        list.add(
            MovieCategory(
                title = "Top Movies",
                movies = moviesList2
            )
        )

        val moviesList3 = mutableListOf<Movie>()
        moviesList3.add(Movie(image = R.drawable.movie_1_1))
        moviesList3.add(Movie(image = R.drawable.movie_2_4))
        moviesList3.add(Movie(image = R.drawable.movie_1_5))
        moviesList3.add(Movie(image = R.drawable.movie_2_5))
        moviesList3.add(Movie(image = R.drawable.movie_2_1))

        list.add(
            MovieCategory(
                title = "Old Movies",
                movies = moviesList3
            )
        )

        val moviesList4 = mutableListOf<Movie>()
        moviesList4.add(Movie(image = R.drawable.movie_2_3))
        moviesList4.add(Movie(image = R.drawable.movie_2_1))
        moviesList4.add(Movie(image = R.drawable.movie_1_1))
        moviesList4.add(Movie(image = R.drawable.movie_3_1))
        moviesList4.add(Movie(image = R.drawable.movie_2_2))
        list.add(
            MovieCategory(
                title = "Top Rated",
                movies = moviesList4
            )
        )
    }

    private fun initAdapter() {
        val layoutManager = GridLayoutManager(context, 1, GridLayoutManager.VERTICAL, false)
        binding?.rvMoviesCategory?.layoutManager = layoutManager
        binding?.rvMoviesCategory?.adapter = movieCategoryAdapter

        movieCategoryAdapter.updateItems(list)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}