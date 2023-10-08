package br.com.portifolio.movieapp.di

import br.com.portifolio.movieapp.data.repository.auth.FirebaseAuthenticationImp
import br.com.portifolio.movieapp.domain.repository.auth.FirebaseAuthentication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsFirebaseAuthenticationImpl(bindsFirebaseAuthenticationImpl: FirebaseAuthenticationImp): FirebaseAuthentication
}