package com.example.movieappazedunet.di

import com.example.movieappazedunet.data.api.ApiService
import com.example.movieappazedunet.data.database.VoteMovieDao
import com.example.movieappazedunet.data.repository.MovieDetailsRepositoryImpl
import com.example.movieappazedunet.data.repository.PopularMoviesRepositoryImpl
import com.example.movieappazedunet.data.repository.VoteMovieRepositoryImpl
import com.example.movieappazedunet.domain.repository.MovieDetailsRepository
import com.example.movieappazedunet.domain.repository.PopularMovieRepository
import com.example.movieappazedunet.domain.repository.VoteMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providePopularMoviesRepository(
        apiService: ApiService
    ): PopularMovieRepository {
        return PopularMoviesRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideMovieDetailsRepository(
        apiService: ApiService
    ): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideVoteMovieRepository(
        dao: VoteMovieDao
    ): VoteMovieRepository {
        return VoteMovieRepositoryImpl(dao)
    }
}