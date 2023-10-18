package br.com.portifolio.movieapp.data.repository.movie

import br.com.portifolio.movieapp.data.api.ServiceApi
import br.com.portifolio.movieapp.data.model.GenresResponse
import br.com.portifolio.movieapp.data.model.MovieResponse
import br.com.portifolio.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
): MovieRepository {
    override suspend fun getGenres(apiKey: String, language: String?): GenresResponse {
        return serviceApi.getGenres(
            apiKey,
            language
        )
    }

    override suspend fun getMoviesByGenre(
        apiKey: String,
        language: String?,
        genreId: Int?
    ): List<MovieResponse> {
        return serviceApi.getMoviesByGenre(
            apiKey,
            language,
            genreId
        ).results ?: emptyList()
    }
}