package br.com.portifolio.movieapp.features.model

import android.os.Parcelable
import br.com.portifolio.movieapp.domain.model.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreFeature(
    val id: Int?,
    val name: String?,
    val movies: List<Movie>?
) : Parcelable