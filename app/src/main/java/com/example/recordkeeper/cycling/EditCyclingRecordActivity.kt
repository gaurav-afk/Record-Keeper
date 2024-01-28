package com.example.recordkeeper.cycling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recordkeeper.R

class EditCyclingRecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_cycling_record)

        var distance = intent.getStringExtra("distance")
        title = "$distance Record"
    }
}