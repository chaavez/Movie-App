package br.com.portifolio.movieapp.domain.repository.movie

import br.com.portifolio.movieapp.data.model.GenresResponse
import br.com.portifolio.movieapp.data.model.MovieResponse

interface MovieRepository {

    suspend fun getGenres(
        apiKey: String,
        language: String?
    ): GenresResponse

    suspend fun getMoviesByGenre(
        apiKey: String,
        language: String?,
        genreId: Int?
    ): List<MovieResponse>
}