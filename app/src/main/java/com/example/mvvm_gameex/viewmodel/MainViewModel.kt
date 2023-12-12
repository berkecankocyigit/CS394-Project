package com.example.mvvm_gameex.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // ObservableFields for two-way data binding
    val title = ObservableField<String>("Title")
    val description = ObservableField<String>()

    // Mutable list to store title and description pairs
    val titleDescriptionList: MutableList<Pair<String, String>> = mutableListOf()

    // Function to add the current title and description to the list
    fun addToTitleDescriptionList() {
        val currentTitle = title.get() ?: ""
        val currentDescription = description.get() ?: ""
        titleDescriptionList.add(Pair(currentTitle, currentDescription))

        // Optionally, you can clear the fields after adding to the list
        title.set("")
        description.set("")
    }

}