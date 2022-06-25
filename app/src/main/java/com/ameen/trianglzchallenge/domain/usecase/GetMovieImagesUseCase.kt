package com.ameen.trianglzchallenge.domain.usecase

import com.ameen.trianglzchallenge.core.wrapper.ResultWrapper
import com.ameen.trianglzchallenge.domain.model.MovieImages
import com.ameen.trianglzchallenge.domain.repository.MovieImagesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieImagesUseCase @Inject constructor(private val repo: MovieImagesRepository) {
    fun execute(movieId: Int): Flow<ResultWrapper<MovieImages>> {
        return repo.getMovieImages(movieId)
    }
}