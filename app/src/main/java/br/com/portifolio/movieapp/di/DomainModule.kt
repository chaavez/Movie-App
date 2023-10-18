package br.com.portifolio.movieapp.di

import br.com.portifolio.movieapp.data.repository.auth.FirebaseAuthenticationImp
import br.com.portifolio.movieapp.data.repository.movie.MovieRepositoryImpl
import br.com.portifolio.movieapp.domain.repository.auth.FirebaseAuthentication
import br.com.portifolio.movieapp.domain.repository.movie.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsFirebaseAuthenticationImpl(
        firebaseAuthenticationImpl: FirebaseAuthenticationImp
    ): FirebaseAuthentication

    @Binds
    abstract fun bindsMovieRepositoryImpl(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}
