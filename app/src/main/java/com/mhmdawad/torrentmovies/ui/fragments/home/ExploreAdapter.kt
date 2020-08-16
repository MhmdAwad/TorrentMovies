package com.mhmdawad.torrentmovies.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.data.model.MoviesItem
import com.mhmdawad.torrentmovies.utils.AdapterListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.explore_layout_rv.view.*
import kotlinx.coroutines.*

class ExploreAdapter(private val adapterListener: AdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val moviesList: MutableList<MoviesItem> = mutableListOf()
    var currentPosition = 0
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ExploreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.explore_layout_rv, parent, false)
        )


    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ExploreViewHolder -> {
                if (position < moviesList.size) {
                    holder.bind(moviesList[position])
                }
            }
        }
    }

    inner class ExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(moviesItem: MoviesItem) = with(itemView) {
            with(moviesItem) {

                currentPosition = adapterPosition
                movieName.text = titleEnglish
                movieRating.rating = (rating?.div(2))?.toFloat()!!
                movieRatingNum.text = rating.toString()
                movieCover.apply {
                    Picasso.get().load(mediumCoverImage).into(this)
                    transitionName = backgroundImageOriginal
                }
                setOnClickListener {
                    adapterListener.openMovie(
                        id!!,
                        backgroundImageOriginal!!,
                        movieCover
                    )
                }
            }
        }
    }

    fun addList(list: List<MoviesItem>) {
        this.moviesList.apply {
            clear()
            addAll(list)
            notifyDataSetChanged()
        }

    }

    fun updateList(list: List<MoviesItem>) {
        GlobalScope.launch { distinctList(list) }
        notifyItemInserted(moviesList.size)
    }

    private suspend fun distinctList(list: List<MoviesItem>) {
        withContext(Dispatchers.IO) {
            moviesList.apply {
                addAll(list)
                val desList = distinctBy { it.id }
                clear()
                addAll(desList)
            }
        }
    }

}