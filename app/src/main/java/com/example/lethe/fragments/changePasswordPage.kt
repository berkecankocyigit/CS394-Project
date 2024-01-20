package com.example.lethe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lethe.R
import com.example.lethe.databinding.FragmentLoginPageBinding
import com.example.lethe.databinding.FragmentUserProfilePageBinding
import com.example.lethe.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth

class changePasswordPage : Fragment() {


    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding : FragmentUserProfilePageBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfilePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        auth = FirebaseAuth.getInstance()
        binding.changePasswordButton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_userProfilePage_to_changePasswordPage)
        }

        binding.logoutButton.setOnClickListener {
            auth.signOut()
            //view?.findNavController()?.navigate(R.id.loginPage)
            view?.findNavController()?.navigate(R.id.action_userProfilePage_to_loginPage)
        }
    }

}