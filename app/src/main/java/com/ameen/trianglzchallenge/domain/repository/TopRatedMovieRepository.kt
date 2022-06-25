package com.ameen.trianglzchallenge.domain.repository

import androidx.paging.PagingData
import com.ameen.trianglzchallenge.core.wrapper.ResultWrapper
import com.ameen.trianglzchallenge.data.model.MovieModel
import com.ameen.trianglzchallenge.domain.model.MovieData
import kotlinx.coroutines.flow.Flow

interface TopRatedMovieRepository {
    fun getTopRatedMovies(): ResultWrapper<Flow<PagingData<MovieData>>>
}