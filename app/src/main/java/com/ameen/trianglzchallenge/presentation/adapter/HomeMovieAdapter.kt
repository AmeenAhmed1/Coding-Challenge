package com.ameen.trianglzchallenge.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ameen.trianglzchallenge.core.util.IMAGE_BASE_URL
import com.ameen.trianglzchallenge.databinding.ItemMovieBinding
import com.ameen.trianglzchallenge.domain.model.MovieData
import com.ameen.trianglzchallenge.presentation.extention.loadImageFromUrl

class HomeMovieAdapter :
    PagingDataAdapter<MovieData, HomeMovieAdapter.MyViewHolder>(HomeMovieDiffUtil) {

    inner class MyViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemMovieBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = getItem(position)

        holder.apply {
            currentItem?.let {
                binding.movieImage.loadImageFromUrl(
                    IMAGE_BASE_URL + currentItem.poster_path,
                    binding.loadingProgress
                )
                binding.movieTitle.text = currentItem.title
                binding.movieYear.text = currentItem.release_date
            }

            binding.itemMovie.setOnClickListener {
                onItemClickListener?.let { currentItem?.let { it1 -> it(it1) } }
            }
        }
    }

    private var onItemClickListener: ((MovieData) -> Unit)? = null
    fun onItemClicked(listener: (MovieData) -> Unit) {
        onItemClickListener = listener
    }

}