package com.example.lethe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lethe.R
import com.example.lethe.viewmodel.MainViewModel

class MainFragment : Fragment() {


    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Find the button by its ID
        val myButton: Button = requireView().findViewById(R.id.createNewDreamButton)
        //viewModel.setTitle("")//mock set title for initializing the firebase and live data
        // Set a click listener for the button
        myButton.setOnClickListener {
            // Handle button click here
            view?.findNavController()?.navigate(R.id.createNewDream)
        }
    }


}