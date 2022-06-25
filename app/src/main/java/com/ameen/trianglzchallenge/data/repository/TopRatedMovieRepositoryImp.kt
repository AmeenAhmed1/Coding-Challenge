package com.ameen.trianglzchallenge.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.ameen.trianglzchallenge.core.wrapper.ResultWrapper
import com.ameen.trianglzchallenge.data.datasource.PAGE_CONFIG
import com.ameen.trianglzchallenge.data.datasource.TopRatedMoviesSource
import com.ameen.trianglzchallenge.data.mapper.DataModelMapper
import com.ameen.trianglzchallenge.data.remote.MoviesApi
import com.ameen.trianglzchallenge.domain.model.MovieData
import com.ameen.trianglzchallenge.domain.repository.TopRatedMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopRatedMovieRepositoryImp @Inject constructor(
    private val api: MoviesApi,
    private val dataModelMapper: DataModelMapper
) : TopRatedMovieRepository {


    override fun getTopRatedMovies(): ResultWrapper<Flow<PagingData<MovieData>>> {
        return try {
            val pagingData = Pager(
                pagingSourceFactory = { TopRatedMoviesSource(api, dataModelMapper) },
                config = PAGE_CONFIG
            ).flow
            ResultWrapper.Success(pagingData)
        } catch (ex: Exception) {
            ResultWrapper.Error(ex.toString())
        }
    }


//    override fun getTopRatedMovies(): Flow<ResultWrapper<List<MovieData>>> {
//        return flow {
//            try {
//                val response = api.getTopRatedMoves(API_KEY, 1)
//                if (response.isSuccessful) {
//                    response.body()?.let {
//                        emit(ResultWrapper.Success(dataModelMapper.movieDataResponseToViewState(it)))
//                    } ?: emit(ResultWrapper.Error("Something Happen Please Try Again!!"))
//                } else {
//                    emit(ResultWrapper.Error(response.errorBody().toString()))
//                }
//            } catch (e: Exception) {
//                emit(ResultWrapper.Error(e.message.toString()))
//            }
//        }
//    }

}