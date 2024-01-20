package com.example.lethe.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class MainViewModel : ViewModel() {

    private val _userID = MutableLiveData<String>()
    val userID: LiveData<String> get() = _userID

    private val _title = MutableLiveData<String>("Title")
    val title: LiveData<String> get() = _title

    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int> get() = _index

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    private val _titleDescriptionList = MutableLiveData<List<Pair<String, String>>>()
    val titleDescriptionList: LiveData<List<Pair<String, String>>> get() = _titleDescriptionList

    // Firestore reference
    private val fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val db: CollectionReference = fireStore.collection("users")

    init {
        // Initialize Firestore collection reference
        _titleDescriptionList.value = emptyList()

    }

    fun addToTitleDescriptionList() {
        // Add new title and description to the list
        val currentTitle = _title.value ?: ""
        val currentDescription = _description.value ?: ""
        val currentList = _titleDescriptionList.value.orEmpty().toMutableList()
        currentList.add(Pair(currentTitle, currentDescription))
        // Update live data
        _titleDescriptionList.value = currentList

        _title.value = ""
        _description.value = ""
        // Update Firestore collection
        db.document(_userID.value.toString()).update("dreamList", _titleDescriptionList.value) //update the dreamList in the database

    }


    private fun fetchTitleDescriptionList() {
        // Listen for changes in the Firestore collection
        db.document(_userID.value.toString()).get().addOnSuccessListener { document ->
            if (document != null) {
                // Document exists
                val data = document.data
                // Handle the data as needed
                val dreamList = data?.get("dreamList") as List<HashMap<String,String>>
                _titleDescriptionList.value = dreamList.map { it
                    val title=it["first"].toString()
                    val description=it["second"].toString()
                    Pair(title,description)
                }
            } else {
                Log.e("login", "User not found")
            }
        }
            .addOnFailureListener { exception ->
                // Handle errors
                // e.g., unable to access Firestore
            }
        Log.e("login", "live array: ${_titleDescriptionList.value.toString()}")
    }



    fun setTitle(title: String) {
        _title.value = title
    }

    fun setDescription(description: String) {
        _description.value = description
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
    fun setUserID(userID: String) {
        _userID.value = userID
        // Fetch the list of titles and descriptions from Firestore
        fetchTitleDescriptionList()
    }
}
