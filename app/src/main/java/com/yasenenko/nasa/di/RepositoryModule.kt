package com.yasenenko.nasa.di

import android.content.Context
import com.yasenenko.nasa.domain.api.NasaApi
import com.yasenenko.nasa.domain.repository.NasaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        nasaApi: NasaApi,
        @ApplicationContext context: Context
    ): NasaRepository {
        return NasaRepository(nasaApi, context)
    }
}