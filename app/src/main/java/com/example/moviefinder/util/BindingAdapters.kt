package com.example.moviefinder.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviefinder.adapters.MultimediaAdapter
import com.example.moviefinder.domain.Movie
import com.example.moviefinder.util.Constants.Companion.BASE_URL_IMAGE

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, posterPath: String?) {
    posterPath?.let {
        val imgUrl = BASE_URL_IMAGE + posterPath
        Glide.with(imgView.context)
            .load(imgUrl)
            .into(imgView)
    }
}

/**
 * Binding adapter used to make cards from list of movies.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?){
    val adapter = recyclerView.adapter as MultimediaAdapter
    adapter.submitList(data)
}

/**
 * Binding adapter used to hide the spinner once data is available.
 */
@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}
