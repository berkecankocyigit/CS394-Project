package com.example.lethe.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val User: User = User(auth.currentUser?.email,viewModel.titleDescriptionList.value?.size)
        binding.user= User
        binding.lifecycleOwner = this
        return binding.root
    }
    companion object{@BindingAdapter("emailColor")
    @JvmStatic
    fun setTextColorBasedOnDreamCount(textView: TextView, dreamCount: Int?) {

        val color= when(dreamCount){
            in 0..5 -> Color.WHITE
            in 6..10 -> Color.YELLOW
            in 10..15 -> Color.GREEN
            else -> Color.BLUE
        }

        textView.setTextColor(color)
    }}
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        auth = FirebaseAuth.getInstance()
        binding.logoutButton.setOnClickListener {
            auth.signOut()
            //view?.findNavController()?.navigate(R.id.loginPage)
            view?.findNavController()?.navigate(R.id.action_userProfilePage_to_loginPage)
        }
    }

}