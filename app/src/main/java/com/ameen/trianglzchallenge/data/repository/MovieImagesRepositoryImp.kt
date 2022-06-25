package com.ameen.trianglzchallenge.data.repository

import com.ameen.trianglzchallenge.core.util.API_KEY
import com.ameen.trianglzchallenge.core.wrapper.ResultWrapper
import com.ameen.trianglzchallenge.data.mapper.DataModelMapper
import com.ameen.trianglzchallenge.data.remote.MoviesApi
import com.ameen.trianglzchallenge.domain.model.MovieImages
import com.ameen.trianglzchallenge.domain.repository.MovieImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieImagesRepositoryImp @Inject constructor(
    private val api: MoviesApi,
    private val movieImageMapper: DataModelMapper
) : MovieImagesRepository {

    override fun getMovieImages(id: Int): Flow<ResultWrapper<MovieImages>> {
        return flow {
            try {
                val response = api.getMovieImages(id, API_KEY)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(
                            ResultWrapper.Success(
                                movieImageMapper.movieImagesResponseToViewState(it)
                            )
                        )
                    } ?: emit(ResultWrapper.Error("Something Happen Please Try Again!!"))
                } else {
                    emit(ResultWrapper.Error(response.errorBody().toString()))
                }
            } catch (e: Exception) {
                emit(ResultWrapper.Error(e.message.toString()))
            }
        }
    }
}