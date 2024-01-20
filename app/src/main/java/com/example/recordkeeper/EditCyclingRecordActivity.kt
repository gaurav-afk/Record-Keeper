package com.example.recordkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EditCyclingRecordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_cycling_record)

        var distance = intent.getStringExtra("distance")
        title = "$distance Record"
    }
}