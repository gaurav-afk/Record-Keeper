package com.example.recordkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recordkeeper.databinding.ActivityEditRunningRecordBinding
import com.example.recordkeeper.databinding.FragmentCyclingBinding

class EditRunningRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRunningRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRunningRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var distance = intent.getStringExtra("distance").toString()
        title = "$distance Record"
    }
}