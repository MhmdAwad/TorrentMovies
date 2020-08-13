package com.mhmdawad.torrentmovies.ui.fragments.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.data.model.TorrentsDetails
import com.mhmdawad.torrentmovies.utils.QualityListener
import kotlinx.android.synthetic.main.movie_quality_dialog_rv.view.*

class MovieDialogAdapter(private val qualityList: List<TorrentsDetails>, private val adapterListener: QualityListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return DialogViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_quality_dialog_rv,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DialogViewHolder -> {
                holder.bind(qualityList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return qualityList.size
    }

    inner class DialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: TorrentsDetails) = with(itemView) {
            firstQualityTxt.text = item.quality
            setOnClickListener { adapterListener.selectQuality(item.url!!)}
        }

    }


}