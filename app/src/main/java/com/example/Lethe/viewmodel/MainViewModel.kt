package com.example.Lethe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _title = MutableLiveData<String>("Title")
    val title: LiveData<String> get() = _title

    private val _index = MutableLiveData<Int>()
    val index: LiveData<Int> get() = _index

    private val _description = MutableLiveData<String>()
    val description: LiveData<String> get() = _description

    private val _titleDescriptionList = MutableLiveData<List<Pair<String, String>>>()
    val titleDescriptionList: LiveData<List<Pair<String, String>>> get() = _titleDescriptionList

    init {
        _titleDescriptionList.value = emptyList()
    }

    fun addToTitleDescriptionList() {
        val currentTitle = _title.value ?: ""
        val currentDescription = _description.value ?: ""

        val currentList = _titleDescriptionList.value.orEmpty().toMutableList()
        currentList.add(Pair(currentTitle, currentDescription))

        _titleDescriptionList.value = currentList

        _title.value = ""
        _description.value = ""
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

}