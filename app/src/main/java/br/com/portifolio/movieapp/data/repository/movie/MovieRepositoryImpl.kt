package br.com.portifolio.movieapp.data.repository.movie

import br.com.portifolio.movieapp.data.api.ServiceApi
import br.com.portifolio.movieapp.data.model.GenresResponse
import br.com.portifolio.movieapp.data.model.MovieResponse
import br.com.portifolio.movieapp.domain.repository.movie.MovieRepository
import br.com.portifolio.movieapp.utils.Constants.Movie.LANGUAGE
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
): MovieRepository {
    override suspend fun getGenres(apiKey: String, language: String?): GenresResponse {
        return serviceApi.getGenres(
            apiKey,
            LANGUAGE
        )
    }

    override suspend fun getMoviesByGenre(
        apiKey: String,
        language: String?,
        genreId: Int?
    ): List<MovieResponse> {
        return serviceApi.getMoviesByGenre(
            apiKey,
            LANGUAGE,
            genreId
        ).results ?: emptyList()
    }
}