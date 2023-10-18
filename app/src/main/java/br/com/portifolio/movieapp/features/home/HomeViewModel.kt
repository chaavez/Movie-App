package br.com.portifolio.movieapp.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.com.portifolio.movieapp.BuildConfig
import br.com.portifolio.movieapp.data.mapper.toFeature
import br.com.portifolio.movieapp.domain.usecase.movie.GetGenresUseCase
import br.com.portifolio.movieapp.domain.usecase.movie.GetMoviesByGenreUseCase
import br.com.portifolio.movieapp.utils.Constants.Movie.LANGUAGE
import br.com.portifolio.movieapp.utils.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val genresUseCase: GetGenresUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
) : ViewModel() {

    fun getGenres() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val genres = genresUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = LANGUAGE
            ).map { it.toFeature() }

            emit(StateView.Success(genres))

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

    fun getMoviesByGenre(genreId: Int?) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val movies = getMoviesByGenreUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = LANGUAGE,
                genreId = genreId
            )

            emit(StateView.Success(movies))

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }
}