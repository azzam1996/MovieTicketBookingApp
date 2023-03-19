package com.azzam.movieticketbookingapp.domain.model

data class MovieCategory(
    var title: String? = null,
    var movies: List<Movie?>
)