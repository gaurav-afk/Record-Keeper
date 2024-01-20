package com.example.recordkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.commit
import com.example.recordkeeper.databinding.ActivityMainBinding

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

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.reset_running -> {
            Toast.makeText(this, "reset_running", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.reset_cycling -> {
            Toast.makeText(this, "reset_cycling", Toast.LENGTH_SHORT).show()
            true
        }
        R.id.reset_all -> {
            Toast.makeText(this, "reset_all", Toast.LENGTH_SHORT).show()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
            true
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
}