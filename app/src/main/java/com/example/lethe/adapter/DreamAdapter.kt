package com.example.lethe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lethe.databinding.DreamLayoutBinding

class DreamAdapter(private val itemClickListener: OnItemClickListener? = null) :
    ListAdapter<Pair<String, String>, DreamAdapter.DreamViewHolder>(DreamDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamViewHolder {
        return DreamViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DreamViewHolder, position: Int) {
        val (title, description) = getItem(position)
        holder.bind(title, description, itemClickListener)
    }

    class DreamViewHolder private constructor(private val binding: DreamLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String, description: String, itemClickListener: OnItemClickListener?) {
            binding.titleView.text = title
            binding.descriptionView.text = description

            // Set onClickListener to handle item click events
            binding.root.setOnClickListener {
                // Trigger the listener when an item is clicked
                itemClickListener?.onItemClick(title, description)
            }
        }

        companion object {
            fun from(parent: ViewGroup): DreamViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = DreamLayoutBinding.inflate(inflater, parent, false)
                return DreamViewHolder(binding)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(title: String, description: String)
    }

    class DreamDiffCallback : DiffUtil.ItemCallback<Pair<String, String>>() {
        override fun areItemsTheSame(oldItem: Pair<String, String>, newItem: Pair<String, String>): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Pair<String, String>, newItem: Pair<String, String>): Boolean {
            return oldItem == newItem
        }
    }
}
