package com.example.lethe.fragments

import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lethe.R
import com.example.lethe.databinding.FragmentLoginPageBinding
import com.example.lethe.databinding.FragmentUserProfilePageBinding
import com.example.lethe.model.User
import com.example.lethe.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth

class userProfilePage : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding : FragmentUserProfilePageBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfilePageBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        auth = FirebaseAuth.getInstance()
        // Inflate the layout for this fragment
        val User: User = User(auth.currentUser?.email,viewModel.titleDescriptionList.value?.size)
        //initialize the user object
        binding.user= User
        binding.lifecycleOwner = this
        return binding.root
    }
    //set the image based on the dream count
    companion object{@BindingAdapter("imageSelector")
    @JvmStatic
    fun setTextColorBasedOnDreamCount(ImageView: ImageView, dreamCount: Int?) {

        val color= when(dreamCount){
            in 0..5 -> R.drawable.first
            in 6..10 -> R.drawable.second
            in 11..15 -> R.drawable.third
            else -> R.drawable.fourth
        }

        ImageView.setImageResource(color)

    }}
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding.logoutButton.setOnClickListener {
            // Handle button click here
            auth.signOut()
            view?.findNavController()?.navigate(R.id.action_userProfilePage_to_loginPage)
        }
    }

}