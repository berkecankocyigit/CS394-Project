package com.example.mvvm_gameex

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mvvm_gameex.databinding.ActivityMainBinding
import com.example.mvvm_gameex.viewmodel.ScoreViewModel

class MainActivity : AppCompatActivity() {

    val scoreViewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewmodel = scoreViewModel
        binding.lifecycleOwner = this

        //val scoreATV: TextView = findViewById(R.id.scoreATV)
        //scoreATV.text = scoreViewModel.scoreA.toString()

        //val plusButtonA: Button = findViewById(R.id.scoreABtn)

//        val scoreA_Observer = Observer<Int> { newValue ->
//            binding.scoreATV.text = newValue.toString()
//        }
//
//        scoreViewModel.scoreA.observe(this, scoreA_Observer)
//
//        val scoreB_Observer = Observer<Int> { newValue ->
//            binding.scoreBTV.text = newValue.toString()
//        }

      //  scoreViewModel.scoreB.observe(this, scoreB_Observer)


        binding.scoreABtn.setOnClickListener {
            scoreViewModel.incrementScore(true)
            //binding.scoreATV.text = scoreViewModel.scoreA.toString()
        }
        binding.scoreBBtn.setOnClickListener {
            scoreViewModel.incrementScore(false)
            //binding.scoreBTV.text = scoreViewModel.scoreB.toString()
        }
    }
}