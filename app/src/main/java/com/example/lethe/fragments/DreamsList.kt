package com.example.lethe.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lethe.R
import com.example.lethe.viewmodel.MainViewModel
import com.example.lethe.adapter.DreamAdapter
import androidx.navigation.findNavController

class DreamsList : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DreamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dreams_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.titleDescriptionList.observe(viewLifecycleOwner) { titleDescriptionList ->
            Log.d("titleDescriptionList", titleDescriptionList.toString())
            adapter = DreamAdapter(titleDescriptionList, object : DreamAdapter.OnItemClickListener {
                override fun onItemClick(title: String, description: String) {
                    viewModel.setIndex(titleDescriptionList.indexOf(Pair(title, description)))
                    view?.findNavController()?.navigate(R.id.dreamDetail)
                }
            })

            recyclerView = view.findViewById(R.id.recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
        }
    }
}