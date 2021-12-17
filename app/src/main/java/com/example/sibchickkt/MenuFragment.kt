package com.example.sibchickkt

import com.example.sibchickkt.MainActivity.Companion.menu1Weeks
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.rec_view)
        val context = view.context
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecMenuAdapter(menu1Weeks!!)
        return view
    }

    init {
        Log.i("STAAAAAAART", " MenuFragment")
    }
}