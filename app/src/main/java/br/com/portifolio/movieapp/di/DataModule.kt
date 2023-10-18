package br.com.portifolio.movieapp.di

import br.com.portifolio.movieapp.data.api.ServiceApi
import br.com.portifolio.movieapp.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providerServiceApi(
        serviceProvider: ServiceProvider
    ): ServiceApi {
        return serviceProvider.createService(ServiceApi::class.java)
    }

    @Provides
    fun providesServiceProvider() = ServiceProvider()
}