package com.ameen.trianglzchallenge.di

import com.ameen.trianglzchallenge.data.mapper.DataModelMapper
import com.ameen.trianglzchallenge.data.remote.MoviesApi
import com.ameen.trianglzchallenge.data.repository.MostPopularMoviesRepositoryImp
import com.ameen.trianglzchallenge.data.repository.MovieImagesRepositoryImp
import com.ameen.trianglzchallenge.data.repository.TopRatedMovieRepositoryImp
import com.ameen.trianglzchallenge.domain.repository.MostPopularMovieRepository
import com.ameen.trianglzchallenge.domain.repository.MovieImagesRepository
import com.ameen.trianglzchallenge.domain.repository.TopRatedMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesTopRatedMovieRepository(api: MoviesApi, dataModelMapper: DataModelMapper) =
        TopRatedMovieRepositoryImp(api, dataModelMapper) as TopRatedMovieRepository

    @Singleton
    @Provides
    fun providesMostPopularMovieRepository(api: MoviesApi, dataModelMapper: DataModelMapper) =
        MostPopularMoviesRepositoryImp(api, dataModelMapper) as MostPopularMovieRepository

    @Singleton
    @Provides
    fun providesMovieImagesRepository(api: MoviesApi, dataModelMapper: DataModelMapper) =
        MovieImagesRepositoryImp(api, dataModelMapper) as MovieImagesRepository

}