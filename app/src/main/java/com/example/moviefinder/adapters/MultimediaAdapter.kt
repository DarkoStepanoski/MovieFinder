package com.example.moviefinder.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.moviefinder.R
import com.example.moviefinder.databinding.MultimediaListItemBinding
import com.example.moviefinder.domain.Movie

class MultimediaAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Movie, MultimediaAdapter.MultimediaViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultimediaViewHolder {
        val withDataBinding: MultimediaListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            MultimediaViewHolder.LAYOUT,
            parent,
            false)
        return MultimediaViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: MultimediaViewHolder, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movie)
        }
        holder.bind(movie)
    }

    class MultimediaViewHolder(private var binding: MultimediaListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(movie: Movie) {
                binding.movie = movie
                binding.executePendingBindings()
            }

        companion object {
            @LayoutRes
            val LAYOUT = R.layout.multimedia_list_item
        }
        }

    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class OnClickListener(val clickListener: (movie: Movie) -> Unit) {
        fun onClick(movie: Movie) = clickListener(movie)
    }

}