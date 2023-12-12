package com.example.mvvm_gameex.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.example.mvvm_gameex.R
import com.example.mvvm_gameex.viewmodel.MainViewModel
import com.example.mvvm_gameex.databinding.FragmentMainBinding
class MainFragment : Fragment() {


    private lateinit var viewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Find the button by its ID
        val myButton: Button = requireView().findViewById(R.id.createNewDreamButton)

        // Set a click listener for the button
        myButton.setOnClickListener {
            // Handle button click here
            view?.findNavController()?.navigate(R.id.createNewDream)
        }
    }


}