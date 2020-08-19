package com.mhmdawad.torrentmovies.ui.fragments.explore

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhmdawad.torrentmovies.R
import com.mhmdawad.torrentmovies.utils.rv_listeners.AdapterListener
import kotlinx.android.synthetic.main.category_layout_rv.view.*

class CategoryAdapter(private val categoryList: MutableList<Pair<String,String>>, private var adapterListener: AdapterListener) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var selectedIndex = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.category_layout_rv, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position].first)
    }

    fun reset(){
        selectedIndex = 0
        notifyDataSetChanged()
    }
    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(name: String) {
            with(itemView) {
                categoryName.text = name
                categoryCardView.setCardBackgroundColor(
                    if (adapterPosition == selectedIndex)
                        Color.parseColor("#1750C4")
                    else
                        Color.parseColor("#1e2747")
                )
            }

            itemView.setOnClickListener {
                selectedIndex = adapterPosition
                notifyDataSetChanged()
                adapterListener.itemClicked(selectedIndex)
            }
        }

    }

}
