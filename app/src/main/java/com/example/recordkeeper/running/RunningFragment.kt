package com.example.recordkeeper.running

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.recordkeeper.databinding.FragmentRunningBinding
import com.example.recordkeeper.editRecord.EditRecordActivity

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
    override fun onResume() {
        super.onResume()
        displayRecords()
    }

    private fun setUpClickListener(){
        binding.container5km.setOnClickListener{ launchRunningRecordScreen(distance = "5km") }
        binding.container10km.setOnClickListener{ launchRunningRecordScreen(distance = "10km") }
        binding.containerHalfMarathon.setOnClickListener{ launchRunningRecordScreen(distance = "Half Marathon") }
        binding.containerMarathon.setOnClickListener{ launchRunningRecordScreen(distance = "Marathon") }
    }

    private fun displayRecords(){
        val runningPreferences: SharedPreferences = requireContext().getSharedPreferences(FILENAME, Context.MODE_PRIVATE)
        binding.textView5kmRecord.text = runningPreferences.getString("5km ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textView5KmDate.text = runningPreferences.getString("5km ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textView10kmRecord.text = runningPreferences.getString("10km ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textView10kmDate.text = runningPreferences.getString("10km ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewHalfMarathonRecord.text = runningPreferences.getString("Half Marathon ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewHalfMarathonDate.text = runningPreferences.getString("Half Marathon ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
        binding.textViewMarathonRecord.text = runningPreferences.getString("Marathon ${EditRecordActivity.SHARED_PREFERENCE_RECORD_KEY}", null)
        binding.textViewMarathonDate.text = runningPreferences.getString("Marathon ${EditRecordActivity.SHARED_PREFERENCE_DATE_KEY}", null)
    }

    private fun launchRunningRecordScreen(distance: String) {
        val intent = Intent(context, EditRecordActivity::class.java)
        intent.putExtra("screen_data", EditRecordActivity.ScreenData(distance, FILENAME, "Time"))
        startActivity(intent)
    }

    companion object{
        const val FILENAME = "running"
    }
}