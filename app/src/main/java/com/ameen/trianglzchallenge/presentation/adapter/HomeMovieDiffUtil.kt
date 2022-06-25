package com.ameen.trianglzchallenge.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ameen.trianglzchallenge.data.model.MovieModel
import com.ameen.trianglzchallenge.domain.model.MovieData

object HomeMovieDiffUtil : DiffUtil.ItemCallback<MovieData>() {
    override fun areItemsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieData, newItem: MovieData): Boolean {
        return oldItem == newItem
    }
}