package com.example.recordkeeper

import android.content.Context
import android.content.DialogInterface
import android.os.Build.VERSION_CODES.P
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.text.method.TextKeyListener
import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.commit
import com.example.recordkeeper.cycling.CyclingFragment
import com.example.recordkeeper.databinding.ActivityMainBinding
import com.example.recordkeeper.running.RunningFragment
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.bottomNav.setOnItemReselectedListener {

            when (it.itemId) {
                R.id.nav_cycling -> onCyclingClicked()
                R.id.nav_running -> onRunningClicked()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val menuClickHandled = when (item.itemId) {
            R.id.reset_running -> {
                showConfirmationDialog(RUNNING_DISPLAY_VALUE)
                true
            }
            R.id.reset_cycling -> {
                showConfirmationDialog(CYCLING_DISPLAY_VALUE)
                true
            }
            R.id.reset_all -> {
                showConfirmationDialog(ALL_DISPLAY_VALUE)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
                true
            }
        }
        refreshCurrentFragment()

        return menuClickHandled
    }

    private fun showConfirmationDialog(selection: String) {
        AlertDialog.Builder(this)
            .setTitle("Reset $selection records")
            .setMessage("Are you sure you want to clear the records?")
            .setPositiveButton("Yes"
            ) { _, _ ->
                when(selection){
                    ALL_DISPLAY_VALUE -> {
                        getSharedPreferences(RunningFragment.FILENAME, Context.MODE_PRIVATE).edit { clear() }
                        getSharedPreferences(CyclingFragment.FILENAME, Context.MODE_PRIVATE).edit { clear() }
                    }
                    RUNNING_DISPLAY_VALUE -> getSharedPreferences(RunningFragment.FILENAME, Context.MODE_PRIVATE).edit { clear() }
                    CYCLING_DISPLAY_VALUE -> getSharedPreferences(CyclingFragment.FILENAME, Context.MODE_PRIVATE).edit { clear() }
                }
                refreshCurrentFragment()
                val snackbar = Snackbar.make(binding.frameContent, "Records cleared successfully!", Snackbar.LENGTH_SHORT)
                showConfirmation(snackbar)
            }
            .setNegativeButton("No", null) // we can also pass null instead of dismiss()
            .show()
    }

    private fun showConfirmation(snackbar: Snackbar) {
        snackbar.anchorView = binding.bottomNav
        snackbar.setAction("Undo") {
            // code to reset the records
        }
        snackbar.show()
    }

    private fun refreshCurrentFragment() {
        when(binding.bottomNav.selectedItemId){
            R.id.nav_running -> onRunningClicked()
            R.id.nav_cycling -> onCyclingClicked()
            else -> {}
        }
    }

    fun onRunningClicked() {
        //handles fragments
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
    }

    fun onCyclingClicked() {
        //handles fragments
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
    }

    companion object{
        const val RUNNING_DISPLAY_VALUE = "cycling"
        const val CYCLING_DISPLAY_VALUE = "running"
        const val ALL_DISPLAY_VALUE = "all"
    }
}