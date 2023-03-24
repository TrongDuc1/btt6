package com.example.btt2screen.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.bumptech.glide.Glide
import com.example.btt2screen.databinding.ItemViewImageBinding
import com.example.btt2screen.model.Movie


class MovieAdapter :  ListAdapter<Movie, MovieAdapter.MovieVH>(MovieDiffUtilCallback()) {

    class MovieDiffUtilCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
    class MovieVH private constructor(var binding: ItemViewImageBinding ) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): MovieVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemViewImageBinding.inflate(layoutInflater, parent, false)
                return MovieVH(binding)
            }
        }

        fun binding(item: Movie) {
            binding.description.text = item.title?.trim()
            binding.status.text = item.overview?.trim()
            val urlImage = "https://image.tmdb.org/t/p/w500${item.posterPath}"
            Glide.with(itemView.context).load(urlImage)
                .into(binding.avatar)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        return MovieVH.from(parent)
    }

    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        val movie = getItem(position)
        holder.binding(movie)
        }
            }




