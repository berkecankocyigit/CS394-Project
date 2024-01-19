package com.example.lethe.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lethe.R
import com.example.lethe.databinding.FragmentSignUpBinding
import com.example.lethe.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth

class signUpPage : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var auth : FirebaseAuth
    private lateinit var binding : FragmentSignUpBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize the binding object
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }
    fun checkAllFields(email: String, password: String, password2: String):Boolean {
        Log.e("email",email)
        Log.e("password",password)
        Log.e("password2",password2)
        if (email.isEmpty()) {
            //binding.editTextTextEmailAddress.error = "Please enter an email"
            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Please enter an email")
                .setPositiveButton("OK", null)
                .show()
            return false
        }
        if (password.isEmpty()) {
            //binding.editTextTextPassword.error = "Please enter a password"
            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Please enter a password")
                .setPositiveButton("OK", null)
                .show()
            return false
        }
        if(password2.isEmpty()){
            //binding.editTextTextPassword2.error = "Please enter a password"
            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Please enter a password")
                .setPositiveButton("OK", null)
                .show()
            return false
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //binding.editTextTextEmailAddress.error = "Please enter a valid email"
            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Please enter a valid email")
                .setPositiveButton("OK", null)
                .show()
            return false
        }
        if (password.toString().length < 6) {
            //binding.editTextTextPassword.error = "Password must be at least 6 characters"
            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Password must be at least 6 characters")
                .setPositiveButton("OK", null)
                .show()
            return false
        }
        if (password.toString() != password.toString()) {
            //binding.editTextTextPassword.error = "Passwords do not match"
            AlertDialog.Builder(context)
                .setTitle("Error")
                .setMessage("Passwords do not match")
                .setPositiveButton("OK", null)
                .show()
            return false
        }
        return true
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val signUpButton1 = binding.signUpButton1
        signUpButton1.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            val password2 = binding.editTextTextPassword2.text.toString()
            if (checkAllFields(email, password, password2)) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            //auth.signOut()
                            Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()
                            view?.findNavController()?.navigate(R.id.mainFragment)
                        } else {
                            Log.e("account creation error: ", it.exception.toString())
                            Toast.makeText(context, "Account creation failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    }

