package com.azzam.movieticketbookingapp.presentation.homepage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azzam.movieticketbookingapp.R
import com.azzam.movieticketbookingapp.databinding.ItemMovieCategoryBinding
import com.azzam.movieticketbookingapp.domain.model.MovieCategory

class MovieCategoryAdapter : RecyclerView.Adapter<MovieCategoryAdapter.MovieCategoryViewHolder>() {

    private val items = mutableListOf<MovieCategory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCategoryViewHolder {
        return MovieCategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_category, null)
        )
    }

    override fun onBindViewHolder(holder: MovieCategoryViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(data: List<MovieCategory>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class MovieCategoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movieCategory: MovieCategory, position: Int) = with(view) {
            val binding = ItemMovieCategoryBinding.bind(view)
            binding.tvCategoryTitle.text = movieCategory.title

            val movieAdapter = MovieAdapter()
            val layoutManager =
                GridLayoutManager(binding.root.context, 1, GridLayoutManager.HORIZONTAL, false)

            binding.rvMovies.layoutManager = layoutManager
            binding.rvMovies.adapter = movieAdapter

            movieAdapter.updateItems(movieCategory.movies)

        }
    }
}