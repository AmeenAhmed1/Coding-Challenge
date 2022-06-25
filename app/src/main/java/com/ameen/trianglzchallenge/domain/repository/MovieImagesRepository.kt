package com.ameen.trianglzchallenge.domain.repository

import com.ameen.trianglzchallenge.core.wrapper.ResultWrapper
import com.ameen.trianglzchallenge.domain.model.MovieImages
import kotlinx.coroutines.flow.Flow

interface MovieImagesRepository {
    fun getMovieImages(id: Int): Flow<ResultWrapper<MovieImages>>
}