package com.ameen.trianglzchallenge.presentation.extention

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.ameen.trianglzchallenge.R
import com.ameen.trianglzchallenge.core.util.IMAGE_BASE_URL
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImageFromUrl(url: String, loadingProgressBar: ProgressBar) {
    Picasso.get()
        .load(IMAGE_BASE_URL + url)
        .error(R.drawable.ic_error)
        .into(this, object : Callback {
            override fun onSuccess() {
                loadingProgressBar.hide()
            }

            override fun onError(e: Exception?) {
                loadingProgressBar.hide()
            }

        })
}