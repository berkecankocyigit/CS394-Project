package com.example.Lethe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Lethe.R

class DreamAdapter(private val dreamList: List<Pair<String, String>>,private val itemClickListener: OnItemClickListener? = null) : RecyclerView.Adapter<DreamAdapter.DreamViewHolder>() {


        class DreamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val titleView: TextView = itemView.findViewById(R.id.titleView)
            val descriptionView: TextView = itemView.findViewById(R.id.descriptionView)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.dream_layout, parent, false)
            return DreamViewHolder(view)
        }

    override fun onBindViewHolder(holder: DreamViewHolder, position: Int) {
        val (title, description) = dreamList[position]
        holder.titleView.text = title
        holder.descriptionView.text = description

        // Set onClickListener to handle item click events
        holder.itemView.setOnClickListener {
            // Trigger the listener when an item is clicked
            itemClickListener?.onItemClick(title, description)
        }
    }

        override fun getItemCount(): Int {
            return dreamList.size
        }
    interface OnItemClickListener {
        fun onItemClick(title: String, description: String)
    }
}
