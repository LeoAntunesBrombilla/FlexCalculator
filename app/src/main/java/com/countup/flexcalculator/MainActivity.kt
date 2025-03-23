package com.countup.flexcalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.countup.flexcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConsumption1.setOnClickListener{
            val intent = Intent(this, GasOptions::class.java)
            startActivity(intent)
        }

        binding.buttonConsumption2.setOnClickListener{
            val intent = Intent(this, GasOptions::class.java)
            startActivity(intent)
        }
    }

}