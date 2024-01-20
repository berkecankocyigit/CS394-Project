package com.example.lethe.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.lethe.R
import com.example.lethe.databinding.FragmentLoginPageBinding
import com.example.lethe.databinding.FragmentSignUpBinding
import com.example.lethe.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class loginPage : Fragment() {

    companion object {
        fun newInstance() = loginPage()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding : FragmentLoginPageBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
        auth = FirebaseAuth.getInstance()
        // Set a click listener for the button
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditTextlogin.text.toString()
            val password = binding.passwordEditTextlogin.text.toString()
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        /*val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
                        val db: CollectionReference = firestore.collection("users")
                        db.get().addOnSuccessListener {
                            //find the document ID of user
                            for (document in it) {
                                if (document.data["email"] == email) {
                                    viewModel.setUserID(document.id)
                                    Log.e("login", viewModel.userID.value.toString())
                                }
                            }
                        }*/
                        viewModel.setUserID(auth.currentUser!!.uid)
                        view?.findNavController()?.navigate(R.id.action_loginPage_to_mainFragment)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.e( "signInWithEmail:failure", task.exception.toString())
                        //    Toast.LENGTH_SHORT).show()
                        AlertDialog.Builder(context)
                            .setTitle("Error")
                            .setMessage("Please enter a valid email and password")
                            .setPositiveButton("OK", null)
                            .show()
                        //updateUI(null)
                    }
                }
            // Handle button click here
        }
        binding.signUpButton.setOnClickListener {
            // Handle button click here
            view?.findNavController()?.navigate(R.id.signUp2)
        }
    }

}