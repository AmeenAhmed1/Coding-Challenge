package com.ameen.trianglzchallenge.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ameen.trianglzchallenge.core.util.API_KEY
import com.ameen.trianglzchallenge.core.util.INITIAL_LOADING_PAGE_NUMBER
import com.ameen.trianglzchallenge.data.mapper.DataModelMapper
import com.ameen.trianglzchallenge.data.remote.MoviesApi
import com.ameen.trianglzchallenge.domain.model.MovieData
import retrofit2.HttpException
import java.io.IOException

class TopRatedMoviesSource constructor(
    private val api: MoviesApi,
    private val dataModelMapper: DataModelMapper
) : PagingSource<Int, MovieData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieData> {

        val pageNumber = params.key ?: INITIAL_LOADING_PAGE_NUMBER

        return try {
            val response = api.getTopRatedMoves(API_KEY, pageNumber)
            val movies = response.body()?.results ?: emptyList()

            LoadResult.Page(
                data = dataModelMapper.movieDataResponseToViewState(movies),
                nextKey = if (movies.isEmpty()) null else pageNumber.plus(1),
                prevKey = null
            )

        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, MovieData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}