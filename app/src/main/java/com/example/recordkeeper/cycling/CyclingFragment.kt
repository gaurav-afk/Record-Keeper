package com.example.recordkeeper.cycling

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.FragmentCyclingBinding
import com.example.recordkeeper.editRecord.EditRecordActivity

class CyclingFragment:Fragment() {

    private lateinit var binding: FragmentCyclingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCyclingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun displayRecords() {
        val cyclingPreferences: SharedPreferences = requireContext().getSharedPreferences("cycling", Context.MODE_PRIVATE)
        binding.textViewLongestRideValue.text = cyclingPreferences.getString("Longest Ride record", null)
        binding.textViewLongestRideDate.text = cyclingPreferences.getString("Longest Ride date", null)
        binding.textViewBiggestClimbValue.text = cyclingPreferences.getString("Biggest Climb record", null)
        binding.textViewBiggestClimbDate.text = cyclingPreferences.getString("Biggest Climb date", null)
        binding.textViewBestAverageSpeedValue.text = cyclingPreferences.getString("Best Average Speed record", null)
        binding.textViewBestAverageSpeedDate.text = cyclingPreferences.getString("Best Average Speed date", null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpClickListener()
    }

    private fun setUpClickListener(){
        binding.containerLongestRide.setOnClickListener{ launchRunningRecordScreen(record = "Longest Ride", recordFieldHint = "Distance") }
        binding.containerBiggestClimb.setOnClickListener{ launchRunningRecordScreen(record = "Biggest Climb", recordFieldHint = "Height") }
        binding.containerBestSpeed.setOnClickListener{ launchRunningRecordScreen(record = "Best Average Speed", recordFieldHint = "Average Speed") }
    }

    private fun launchRunningRecordScreen(record: String, recordFieldHint: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra("screen_data", EditRecordActivity.ScreenData(record, "cycling", recordFieldHint))
        startActivity(intent)
    }
}