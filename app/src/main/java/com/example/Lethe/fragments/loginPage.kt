package com.example.Lethe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.Lethe.R
import com.example.Lethe.viewmodel.MainViewModel

class loginPage : Fragment() {

    companion object {
        fun newInstance() = loginPage()
    }

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        val myButton: Button = requireView().findViewById(R.id.loginButton)

        // Set a click listener for the button
        myButton.setOnClickListener {
            // Handle button click here
            view?.findNavController()?.navigate(R.id.mainFragment)
        }
    }

}