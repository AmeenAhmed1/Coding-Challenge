package com.ameen.trianglzchallenge.presentation.fragment.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.ameen.trianglzchallenge.core.wrapper.ResultWrapper
import com.ameen.trianglzchallenge.domain.model.MovieData
import com.ameen.trianglzchallenge.domain.usecase.GetMostPopularMoviesUseCase
import com.ameen.trianglzchallenge.domain.usecase.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getMostPopularMoviesUseCase: GetMostPopularMoviesUseCase
) : ViewModel() {

    private val _movieDataList: MutableStateFlow<ResultWrapper<Flow<PagingData<MovieData>>>> =
        MutableStateFlow(ResultWrapper.Loading)
    val movieDataList = _movieDataList

    fun getTopRatedMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getTopRatedMoviesUseCase.execute()
            _movieDataList.emit(result)
        }
    }

    fun getMostPopularMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getMostPopularMoviesUseCase.execute()
            _movieDataList.emit(result)
        }
    }
}