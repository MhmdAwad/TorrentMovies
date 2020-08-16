package com.mhmdawad.torrentmovies.ui.fragments.rank

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.data.model.MoviesItem
import com.mhmdawad.torrentmovies.utils.AdapterListener
import com.mhmdawad.torrentmovies.utils.addCategories
import com.mhmdawad.torrentmovies.utils.downloadImage
import kotlinx.android.synthetic.main.movies_rank_rv.view.*

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

    fun addList(list: List<MoviesItem>){
        moviesList.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    inner class RankViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MoviesItem) = with(itemView) {
            startAnimation(AnimationUtils.loadAnimation(this.context, R.anim.slide_right_to_left))
            rankMovieSort.text = if(adapterPosition > 99) "99+" else (adapterPosition+1).toString().format(2)
            rankMovieName.text = item.titleEnglish
            rankMovieRating.rating = (item.rating?.div(2))?.toFloat()!!
            rankMovieRatingTxt.text = item.rating.toString()
            rankMovieCategory.addCategories(item.genres!!)
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