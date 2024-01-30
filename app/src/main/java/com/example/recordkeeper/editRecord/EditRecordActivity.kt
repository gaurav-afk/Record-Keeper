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
import android.view.MenuItem
import androidx.core.content.edit
import androidx.preference.Preference
import com.example.recordkeeper.databinding.ActivityEditRecordBinding
import java.io.Serializable


class EditRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditRecordBinding
    private val screenData: ScreenData by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA, ScreenData::class.java) as ScreenData
        } else {
            @Suppress("DEPRECATION")
            intent.getSerializableExtra(INTENT_EXTRA_SCREEN_DATA) as ScreenData
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
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
                "${screenData.record} $SHARED_PREFERENCE_RECORD_KEY",
                null
            )
        )
        binding.editTextDate.setText(recordPreferences.getString("${screenData.record} $SHARED_PREFERENCE_DATE_KEY", null))
    }

    private fun saveRecord() {
        val record = binding.editTextRecord.text.toString()
        val date = binding.editTextDate.text.toString()
        recordPreferences.edit {
            putString("${screenData.record} $SHARED_PREFERENCE_RECORD_KEY", record)
            putString("${screenData.record} $SHARED_PREFERENCE_DATE_KEY", date)
        }
    }

    private fun clearRecord() {
        recordPreferences.edit {
            remove("${screenData.record} $SHARED_PREFERENCE_RECORD_KEY")
            remove("${screenData.record} $SHARED_PREFERENCE_DATE_KEY")
        }
    }

    data class ScreenData(
        val record: String,
        val sharedPreferencesName: String,
        val recordFieldHint: String
    ) : Serializable

    companion object{
        const val SHARED_PREFERENCE_RECORD_KEY = "record"
        const val SHARED_PREFERENCE_DATE_KEY = "date"
        const val INTENT_EXTRA_SCREEN_DATA = "screen_data"
    }
}