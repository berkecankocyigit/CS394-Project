package com.example.lethe.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.lethe.R
import com.example.lethe.databinding.FragmentDreamDetailBinding
import com.example.lethe.model.Dream
import com.example.lethe.viewmodel.MainViewModel

class dreamDetail : Fragment() {

    companion object {
        fun newInstance() = dreamDetail()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var dream: Dream

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDreamDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dream_detail, container, false
        )
        viewModel.titleDescriptionList.observe(viewLifecycleOwner,  {titleDescriptionList ->
            Log.d("titleDescriptionList", titleDescriptionList.toString())
            viewModel.index.observe(viewLifecycleOwner,  {index ->
                Log.d("first", titleDescriptionList.get(index).first)
                Log.d("second", titleDescriptionList.get(index).second)
                dream = Dream(titleDescriptionList.get(index).first,titleDescriptionList.get(index).second)
                Log.d("dream", dream.toString())
                binding.dream = Dream(titleDescriptionList.get(index).first,titleDescriptionList.get(index).second)
                binding.lifecycleOwner = this
            })

        })
        // Set the view model for data binding


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(DreamDetailViewModel::class.java)

    }

}