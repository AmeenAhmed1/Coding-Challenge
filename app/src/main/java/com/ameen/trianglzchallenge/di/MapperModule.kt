package com.ameen.trianglzchallenge.di


import com.ameen.trianglzchallenge.data.mapper.DataModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideMovieGenreMapper(): DataModelMapper = DataModelMapper()

}