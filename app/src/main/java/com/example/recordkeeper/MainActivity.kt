package com.example.recordkeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.commit
import com.example.recordkeeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.bottomNav.setOnItemReselectedListener {
            if(it.itemId == R.id.nav_cycling){
                onCyclingClicked()
            }
            else if(it.itemId == R.id.nav_running){
                onRunningClicked()
            }
        }



    }

    fun onRunningClicked(){
        //handles fragments
        supportFragmentManager.commit {
            replace(R.id.frame_content, RunningFragment())
        }
    }

    fun onCyclingClicked(){
        //handles fragments
        supportFragmentManager.commit {
            replace(R.id.frame_content, CyclingFragment())
        }
    }
}