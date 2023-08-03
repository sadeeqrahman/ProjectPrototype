package com.sadeeq.app.projectprototype.adapters

import android.annotation.SuppressLint
import android.content.Context
import coil.size.Scale
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sadeeq.app.projectprototype.R
import com.sadeeq.app.projectprototype.databinding.ItemMoviesBinding
import com.sadeeq.app.projectprototype.models.MoviesListResponse
import com.sadeeq.app.projectprototype.utils.POSTER_BASE_URL
import javax.inject.Inject

class MoviesAdapter @Inject() constructor() :
    PagingDataAdapter<MoviesListResponse.Result, MoviesAdapter.ViewHolder>(differCallback) {

    private lateinit var binding: ItemMoviesBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemMoviesBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: MoviesListResponse.Result) {
            binding.movieViewMdel = item
            binding.apply {
                tvMovieName.text = item.title
                tvMovieDateRelease.text = item.releaseDate
                tvRate.text = item.voteAverage.toString()
                val moviePosterURL = POSTER_BASE_URL + item?.posterPath
                imgMovie.load(moviePosterURL){
                    crossfade(true)
                    placeholder(R.drawable.poster_placeholder)
                    scale(Scale.FILL)
                }
                tvLang.text = item.originalLanguage

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((MoviesListResponse.Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (MoviesListResponse.Result) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<MoviesListResponse.Result>() {
            override fun areItemsTheSame(
                oldItem: MoviesListResponse.Result,
                newItem: MoviesListResponse.Result
            ): Boolean {
                return oldItem.id == oldItem.id
            }

            override fun areContentsTheSame(
                oldItem: MoviesListResponse.Result,
                newItem: MoviesListResponse.Result
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}
