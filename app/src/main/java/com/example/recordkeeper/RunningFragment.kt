package com.example.recordkeeper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.FragmentCyclingBinding
import com.example.recordkeeper.databinding.FragmentRunningBinding

class RunningFragment:Fragment() {

    private lateinit var binding: FragmentRunningBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRunningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // runs before view gets created
        super.onViewCreated(view, savedInstanceState)
        setUpClickListener()
    }

    private fun setUpClickListener(){

        binding.container5km.setOnClickListener{ launchRunningRecordScreen(distance = "5km") }
        binding.container10km.setOnClickListener{ launchRunningRecordScreen(distance = "10km") }
        binding.containerHalfMarathon.setOnClickListener{ launchRunningRecordScreen(distance = "Half-Marathon") }
        binding.containerMarathon.setOnClickListener{ launchRunningRecordScreen(distance = "Marathon") }
    }

    private fun launchRunningRecordScreen(distance: String) {
        val intent = Intent(context, EditRunningRecordActivity::class.java)
        intent.putExtra("distance", distance)
        startActivity(intent)
    }
}