package com.ameen.trianglzchallenge.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.ameen.trianglzchallenge.R
import com.ameen.trianglzchallenge.core.util.RECYCLER_VIEW_GRID_SPAN_SIZE
import com.ameen.trianglzchallenge.core.wrapper.ResultWrapper
import com.ameen.trianglzchallenge.databinding.FragmentHomeBinding
import com.ameen.trianglzchallenge.presentation.adapter.HomeMovieAdapter
import com.ameen.trianglzchallenge.presentation.adapter.HomeMovieLoadStateAdapter
import com.ameen.trianglzchallenge.presentation.extention.hide
import com.ameen.trianglzchallenge.presentation.extention.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private lateinit var recAdapter: HomeMovieAdapter
    private val homeViewModel: HomeViewModel by viewModels()

    private var sortType: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObservers()
        setChipCheck()

    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            homeViewModel.movieDataList.collectLatest {
                when (it) {
                    is ResultWrapper.Success -> {
                        it.value.collectLatest {
                            recAdapter.submitData(it)
                        }
                    }
                    is ResultWrapper.Error -> {
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    private fun initRecyclerView() {

        if (!this::recAdapter.isInitialized) {

            recAdapter = HomeMovieAdapter()

            binding.topMovieRecycler.apply {
                adapter = recAdapter.withLoadStateHeaderAndFooter(
                    header = HomeMovieLoadStateAdapter { recAdapter.retry() },
                    footer = HomeMovieLoadStateAdapter { recAdapter.retry() }
                )
                layoutManager =
                    GridLayoutManager(requireContext(), RECYCLER_VIEW_GRID_SPAN_SIZE)
            }

            recAdapter.onItemClicked {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it)
                findNavController().navigate(action)
            }

//            homeViewModel.getTopRatedMovies()
//            handleSort()
            handleSort()

            recAdapter.addLoadStateListener { loadState ->
                if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading)
                    binding.loadingProgress.show()
                else {
                    binding.loadingProgress.hide()

                    val errorState = when {
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                    errorState?.let {
                        Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

        }

    }

    private fun setChipCheck() {
        if (sortType == 1)
            binding.filterTopRated.isChecked = true
        else
            binding.filterMostPopular.isCheckable = false
    }

    private fun handleSort() {
        binding.filterContainer.setOnCheckedStateChangeListener { group, checkedIds ->
            when (checkedIds.first()) {
                R.id.filter_top_rated -> {
                    sortType = 1
                    recAdapter.submitData(lifecycle, PagingData.empty())
                    homeViewModel.getTopRatedMovies()
                }
                R.id.filter_most_popular -> {
                    sortType = 2
                    recAdapter.submitData(lifecycle, PagingData.empty())
                    homeViewModel.getMostPopularMovies()
                }
            }
        }
        setChipCheck()
    }
}