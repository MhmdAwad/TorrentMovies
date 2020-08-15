package com.mhmdawad.torrentmovies.ui.fragments.stream

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.masterwok.opensubtitlesandroid.models.OpenSubtitleItem
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.utils.AdapterListener
import com.mhmdawad.torrentmovies.utils.SubtitleListener
import kotlinx.android.synthetic.main.movie_subtitle_dialog_rv.view.*

class MovieSubtitlesAdapter(private val subtitleList: Array<OpenSubtitleItem>,
                            private val adapterListener: SubtitleListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return SubtitlesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_subtitle_dialog_rv,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SubtitlesViewHolder -> {
                holder.bind(subtitleList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return subtitleList.size
    }

    inner class SubtitlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: OpenSubtitleItem) = with(itemView) {
            movieSubName.text = item.SubFileName
            movieSubLanguage.text = item.LanguageName
            setOnClickListener {
                adapterListener.onSubtitleClicked(item)
            }
        }
    }

}