package com.example.lethe.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.lethe.R
import com.example.lethe.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class signUp : Fragment() {

    companion object {
        fun newInstance() = signUp()
    }

    private lateinit var viewModel: SignUpViewModel
    private lateinit var auth : FirebaseAuth
    private lateinit var binding : FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }
    fun checkAllFields():Boolean {
        if (binding.editTextTextEmailAddress.text.toString().isEmpty()) {
            binding.editTextTextEmailAddress.error = "Please enter an email"
            return false
        }
        if (binding.editTextTextPassword.text.toString().isEmpty()) {
            binding.editTextTextPassword.error = "Please enter a password"
            return false
        }
        if(binding.editTextTextPassword2.text.toString().isEmpty()){
            binding.editTextTextPassword2.error = "Please enter a password"
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(binding.editTextTextEmailAddress.text.toString()).matches()){
            binding.editTextTextEmailAddress.error = "Please enter a valid email"
            return false
        }
        if (binding.editTextTextPassword.text.toString().length < 6) {
            binding.editTextTextPassword.error = "Password must be at least 6 characters"
            return false
        }
        return true
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        // TODO: Use the ViewModel
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        binding.signUp.setOnClickListener {
            if(checkAllFields()){
                auth.createUserWithEmailAndPassword(binding.editTextTextEmailAddress.text.toString(),binding.editTextTextPassword.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            auth.signOut()
                            Toast.makeText(context,"Account created successfully",Toast.LENGTH_SHORT).show()
                            view?.findNavController()?.navigate(R.id.mainFragment)
                        }else{
                            Log.e("account creation error: ",it.exception.toString())
                            Toast.makeText(context,"Account creation failed",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

    }

}