package com.example.Lethe.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.Lethe.databinding.FragmentCreateNewDreamBinding
import com.example.Lethe.viewmodel.MainViewModel


class createNewDream : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentCreateNewDreamBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNewDreamBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_create_new_dream, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Your existing button click listener
        binding.saveButton.setOnClickListener {
            // Handle button click here
            viewModel.setTitle(binding.titleEditText.text.toString())
            viewModel.setDescription(binding.descriptionEditText.text.toString())
            Log.d("eklenen",viewModel.titleDescriptionList.toString())
            viewModel.addToTitleDescriptionList()
        }
    }
}