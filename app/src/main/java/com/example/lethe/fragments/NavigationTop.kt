package com.example.lethe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.lethe.MainActivity
import com.example.lethe.R


class NavigationTop : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_navigation_top, container, false)

        val profileButton: Button = view.findViewById(R.id.profileButton)
        profileButton.setOnClickListener {
            // Get the MainActivity reference and open/close the drawer
            val mainActivity = activity as? MainActivity
            mainActivity?.openProfilePage()
        }
        val historyButtton: Button = view.findViewById(R.id.historyButton)
        historyButtton.setOnClickListener {
            // Get the MainActivity reference and open/close the drawer
            val mainActivity = activity as? MainActivity
            mainActivity?.openDreamsListPage()
        }

        return view
    }

}