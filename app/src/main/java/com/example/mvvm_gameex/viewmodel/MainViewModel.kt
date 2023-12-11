package com.example.mvvm_gameex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _scoreA = MutableLiveData<Int>(0)
    val scoreA: LiveData<Int>
        get() = _scoreA
    private val _scoreB = MutableLiveData<Int>(0)
    val scoreB: LiveData<Int>
        get() = _scoreB

//    val scoreB:Int
//        get()= _scoreA

    fun incrementScore(isTeamA: Boolean){
        if (isTeamA) {
            _scoreA.value = _scoreA.value!! + 1

        }
        else{
            _scoreB.value = _scoreB.value!! + 1

        }

    }
}