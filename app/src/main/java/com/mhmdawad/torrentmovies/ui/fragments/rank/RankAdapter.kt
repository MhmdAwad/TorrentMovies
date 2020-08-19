package com.mhmdawad.torrentmovies.ui.fragments.rank

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.data.model.MoviesItem
import com.mhmdawad.torrentmovies.utils.*
import com.mhmdawad.torrentmovies.utils.rv_listeners.AdapterListener
import kotlinx.android.synthetic.main.movies_rank_rv.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RankAdapter(private val adapterListener: AdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val moviesList:MutableList<MoviesItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return RankViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movies_rank_rv,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RankViewHolder -> {
                holder.bind(moviesList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }


    fun addList(list: List<MoviesItem>) {
        this.moviesList.addList(list)
        notifyDataSetChanged()
    }

    fun updateList(list: List<MoviesItem>) {
        GlobalScope.launch { moviesList.distinctList(list) }
        notifyItemInserted(moviesList.size)
    }

    inner class RankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MoviesItem) = with(itemView) {
            startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.slide_right_to_left))
            rankMovieSort.text = if(adapterPosition > 98) "99+" else (adapterPosition+1).toString().format(2)
            rankMovieName.text = item.titleEnglish
            rankMovieRating.rating = (item.rating?.div(2))?.toFloat()!!
            rankMovieRatingTxt.text = item.rating.toString()
            rankMovieCategory.text = item.genres?.get(0)
            rankMovieYear.text = item.year.toString()
            rankMovieImg.apply {
                downloadImage(item.mediumCoverImage)
                transitionName = item.backgroundImageOriginal
            }
            setOnClickListener {
                adapterListener.openMovie(item.id!!, rankMovieImg)
            }
        }
    }

}