package br.com.portifolio.movieapp.domain.usecase.movie

import br.com.portifolio.movieapp.data.mapper.toDomain
import br.com.portifolio.movieapp.domain.model.Genre
import br.com.portifolio.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    suspend fun invoke(apiKey: String, language: String?): List<Genre> {
        return movieRepository.getGenres(
            apiKey,
            language
        ).genres?.map { it.toDomain() } ?: emptyList()
    }
}