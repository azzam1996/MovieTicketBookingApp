package com.azzam.movieticketbookingapp.presentation.homepage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.azzam.movieticketbookingapp.R
import com.azzam.movieticketbookingapp.databinding.ItemMovieBinding
import com.azzam.movieticketbookingapp.domain.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val items = mutableListOf<Movie?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, null)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(data: List<Movie?>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie?, position: Int) = with(view) {
            val binding = ItemMovieBinding.bind(view)
            movie?.image?.let {
                binding.ivPoster.load(it) {
                    allowHardware(false)
                }
            }
        }
    }
}