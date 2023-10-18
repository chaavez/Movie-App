package br.com.portifolio.movieapp.domain.usecase.movie

import br.com.portifolio.movieapp.data.mapper.toDomain
import br.com.portifolio.movieapp.domain.model.Genre
import br.com.portifolio.movieapp.domain.model.Movie
import br.com.portifolio.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetMoviesByGenreUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun invoke(apiKey: String, language: String?, genreId: Int?): List<Movie> {
        return movieRepository.getMoviesByGenre(
            apiKey,
            language,
            genreId
        ).map { it.toDomain() }
    }
}