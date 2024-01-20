package com.example.lethe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.lethe.R
import com.example.lethe.adapter.DreamAdapter
import androidx.fragment.app.activityViewModels
import com.example.lethe.databinding.FragmentDreamDetailBinding
import com.example.lethe.model.Dream
import com.example.lethe.viewmodel.MainViewModel

class dreamDetail : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentDreamDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dream_detail, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeViewModelData()
    }

    private fun setupRecyclerView() {
        val adapter = DreamAdapter(object : DreamAdapter.OnItemClickListener {
            override fun onItemClick(title: String, description: String) {
                // Handle item click as needed
            }
        })

        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModelData() {
        viewModel.titleDescriptionList.observe(viewLifecycleOwner) { titleDescriptionList ->
            // Update the adapter with the new data
            (binding.recyclerView.adapter as? DreamAdapter)?.submitList(titleDescriptionList)
        }

        viewModel.index.observe(viewLifecycleOwner) { index ->
            // Update other UI elements based on the selected index if needed
            val (title, description) = viewModel.titleDescriptionList.value?.get(index) ?: Pair("", "")
            binding.dream = Dream(title, description)
        }
    }
}