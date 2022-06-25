package com.ameen.trianglzchallenge.domain.usecase

import androidx.paging.PagingData
import com.ameen.trianglzchallenge.core.wrapper.ResultWrapper
import com.ameen.trianglzchallenge.data.model.MovieModel
import com.ameen.trianglzchallenge.domain.model.MovieData
import com.ameen.trianglzchallenge.domain.repository.TopRatedMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(private val repo: TopRatedMovieRepository) {
    fun execute(): ResultWrapper<Flow<PagingData<MovieData>>> {
        return repo.getTopRatedMovies()
    }
}