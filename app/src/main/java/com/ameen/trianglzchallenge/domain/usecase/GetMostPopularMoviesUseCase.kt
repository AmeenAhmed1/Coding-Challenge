package com.ameen.trianglzchallenge.domain.usecase

import androidx.paging.PagingData
import com.ameen.trianglzchallenge.core.wrapper.ResultWrapper
import com.ameen.trianglzchallenge.domain.model.MovieData
import com.ameen.trianglzchallenge.domain.repository.MostPopularMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMostPopularMoviesUseCase @Inject constructor(private val repo: MostPopularMovieRepository) {
    fun execute(): ResultWrapper<Flow<PagingData<MovieData>>> {
        return repo.getMostPopularMovies()
    }
}