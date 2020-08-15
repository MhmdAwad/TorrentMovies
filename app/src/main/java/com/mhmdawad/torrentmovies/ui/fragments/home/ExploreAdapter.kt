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
    private var loading = true

    companion object {
        private const val LOADING_VIEW = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            LOADING_VIEW -> LoadingViewHolder(
                inflater.inflate(
                    R.layout.loading_layout_rv,
                    parent,
                    false
                )
            )
            else -> ExploreViewHolder(inflater.inflate(R.layout.explore_layout_rv, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (position == moviesList.size && loading) 1
        else 0


    override fun getItemCount(): Int = moviesList.size + 1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ExploreViewHolder -> {
                    if(position < moviesList.size){
                        holder.bind(moviesList[position])
                    }
            }
        }
    }

    inner class LoadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}

    inner class ExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(moviesItem: MoviesItem) = with(itemView) {
            movieName.text = moviesItem.titleEnglish
            movieRating.rating = (moviesItem.rating?.div(2))?.toFloat()!!
            movieRatingNum.text = moviesItem.rating.toString()
            movieCover.apply {
                Picasso.get().load(moviesItem.mediumCoverImage).into(this)
                transitionName = moviesItem.backgroundImageOriginal
            }
            setOnClickListener {
                adapterListener.openMovie(
                    moviesItem.id!!,
                    moviesItem.backgroundImageOriginal!!,
                    movieCover
                )
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
        notifyItemInserted(moviesList.size-1)
    }

    private suspend fun distinctList(list: List<MoviesItem>) {
        withContext(Dispatchers.IO) {
            moviesList.apply {
                addAll(list)
                val desList = distinctBy { it.imdbCode }
                clear()
                addAll(desList)
            }
        }
    }

    fun stopLoading() {
        loading = false
        notifyDataSetChanged()
    }

    fun loadMore(): Boolean {
        return loading
    }

    fun keepLoading() {
        loading = true
    }


}