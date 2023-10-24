package br.com.portifolio.movieapp.features.main.bottombar.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.portifolio.movieapp.databinding.GenreItemBinding
import br.com.portifolio.movieapp.features.model.GenreFeature

class GenreMovieAdapter(
    private val showAllListener: (Int) -> Unit
) : ListAdapter<GenreFeature, GenreMovieAdapter.HomeViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<GenreFeature>() {
            override fun areItemsTheSame(
                oldItem: GenreFeature,
                newItem: GenreFeature
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: GenreFeature,
                newItem: GenreFeature
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            GenreItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val genre = getItem(position)

        holder.binding.genreName.text = genre.name

        val movieAdapter = MovieAdapter(holder.binding.root.context)
        val layoutManager =
            LinearLayoutManager(holder.binding.root.context, LinearLayoutManager.HORIZONTAL, false
            )

        holder.binding.textShowAll.setOnClickListener {
            genre.id?.let { showAllListener(it) }
        }

        holder.binding.moviesRecyclerView.layoutManager = layoutManager
        holder.binding.moviesRecyclerView.adapter = movieAdapter
        holder.binding.moviesRecyclerView.setHasFixedSize(true)
        movieAdapter.submitList(genre.movies)
    }

    inner class HomeViewHolder(val binding: GenreItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}