package com.ameen.trianglzchallenge.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import com.ameen.trianglzchallenge.core.wrapper.ResultWrapper
import com.ameen.trianglzchallenge.data.datasource.MostPopularMoviesSource
import com.ameen.trianglzchallenge.data.datasource.PAGE_CONFIG
import com.ameen.trianglzchallenge.data.mapper.DataModelMapper
import com.ameen.trianglzchallenge.data.remote.MoviesApi
import com.ameen.trianglzchallenge.domain.model.MovieData
import com.ameen.trianglzchallenge.domain.repository.MostPopularMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MostPopularMoviesRepositoryImp @Inject constructor(
    private val api: MoviesApi,
    private val dataModelMapper: DataModelMapper
) : MostPopularMovieRepository {

    override fun getMostPopularMovies(): ResultWrapper<Flow<PagingData<MovieData>>> {
        return try {
            val pagingData = Pager(
                pagingSourceFactory = { MostPopularMoviesSource(api, dataModelMapper) },
                config = PAGE_CONFIG
            ).flow
            ResultWrapper.Success(pagingData)
        } catch (ex: Exception) {
            ResultWrapper.Error(ex.toString())
        }
    }
}