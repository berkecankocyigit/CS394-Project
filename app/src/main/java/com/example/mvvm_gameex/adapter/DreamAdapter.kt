package com.example.mvvm_gameex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_gameex.R

class DreamAdapter(private val dreamList: List<Pair<String, String>>) : RecyclerView.Adapter<DreamAdapter.DreamViewHolder>() {


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

            // Set onClickListener to navigate to the detailed view or perform any other action
            holder.itemView.setOnClickListener {
                // Use a listener or navigate to the detailed view fragment
                // For example: listener?.onDreamItemClicked(title, description)
            }
        }

        override fun getItemCount(): Int {
            return dreamList.size
        }
}