package com.example.lethe.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lethe.R
import com.example.lethe.adapter.DreamAdapter
import com.example.lethe.databinding.FragmentDreamsListBinding
import com.example.lethe.viewmodel.MainViewModel

class DreamsList : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentDreamsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dreams_list, container, false
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

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModelData() {
        viewModel.titleDescriptionList.observe(viewLifecycleOwner) { titleDescriptionList ->
            // Update the adapter with the new data
            (binding.recyclerView.adapter as? DreamAdapter)?.submitList(titleDescriptionList)
        }
    }
}