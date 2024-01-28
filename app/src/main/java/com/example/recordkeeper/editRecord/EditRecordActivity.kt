package com.example.recordkeeper.editRecord

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Build.VERSION_CODES.P
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.system.Os.bind
import android.system.Os.remove
import androidx.core.content.edit
import androidx.preference.Preference
import com.example.recordkeeper.databinding.ActivityEditRecordBinding
import com.example.recordkeeper.databinding.ActivityEditRunningRecordBinding
import java.io.Serializable

class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding
    private val screenData: ScreenData by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("screen_data", ScreenData::class.java) as ScreenData
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra("screen_data") as ScreenData
        }
    }
    private val recordPreferences by lazy {
        getSharedPreferences(
            screenData.sharedPreferencesName,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
        displayRecord()

    }

    fun setupUi() {
        title = "${screenData.record} Record" // to set appbar text
        binding.textInputRecord.hint = screenData.recordFieldHint
        displayRecord()
        binding.buttonSave.setOnClickListener {
            saveRecord()
            finish()
        }
        binding.buttonDelete.setOnClickListener {
            clearRecord()
            finish()
        }
    }

    fun displayRecord() {
        binding.editTextRecord.setText(
            recordPreferences.getString(
                "${screenData.record} record",
                null
            )
        )
        binding.editTextDate.setText(recordPreferences.getString("${screenData.record} date", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()
        recordPreferences.edit {
            putString("${screenData.record} record", record)
            putString("${screenData.record} date", date)
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("${screenData.record} record")
            remove("${screenData.record} date")
        }
    }

    data class ScreenData(
        val record: String,
        val sharedPreferencesName: String,
        val recordFieldHint: String
    ) : Serializable
}