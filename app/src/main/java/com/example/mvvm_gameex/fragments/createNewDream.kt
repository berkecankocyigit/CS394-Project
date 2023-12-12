package com.example.mvvm_gameex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_gameex.R
import com.example.mvvm_gameex.databinding.FragmentCreateNewDreamBinding
import com.example.mvvm_gameex.viewmodel.MainViewModel


class createNewDream : Fragment() {

    private lateinit var viewModel: MainViewModel
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
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Your existing button click listener
        binding.saveButton.setOnClickListener {
            // Handle button click here
            viewModel.title.set(binding.titleEditText.text.toString())
            viewModel.description.set(binding.descriptionEditText.text.toString())
            viewModel.addToTitleDescriptionList()
        }
    }
}