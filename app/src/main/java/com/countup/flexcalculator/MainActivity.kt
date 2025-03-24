package com.countup.flexcalculator

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.countup.flexcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val REQUEST_FUEL_TYPE_1 = 100
    private val REQUEST_FUEL_TYPE_2 = 101

    private val fuelLauncher1 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        result -> if(result.resultCode == RESULT_OK) {
            val selectedFuel = result.data?.getStringExtra("SELECTED_FUEL")
            binding.textViewConsumption1.text = selectedFuel
        }
    }

    private val fuelLauncher2 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
            result -> if(result.resultCode == RESULT_OK) {
            val selectedFuel = result.data?.getStringExtra("SELECTED_FUEL")
            binding.textViewConsumption.text = selectedFuel
        }
    }

    private fun fuelCalculator(quantityFuel1: Double, priceFuel1: Double, quantityFuel2: Double, priceFuel2: Double): CharSequence? {
        val result1 = quantityFuel1 * priceFuel1
        val result2 = quantityFuel2 * priceFuel2

        return if(result1 > result2) {
            binding.textViewConsumption1.text
        } else {
            binding.textViewConsumption.text
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonConsumption1.setOnClickListener {
            val intent = Intent(this, GasOptions::class.java)
            fuelLauncher1.launch(intent)
        }

        binding.buttonConsumption2.setOnClickListener {
            val intent = Intent(this, GasOptions::class.java)
            fuelLauncher2.launch(intent)
        }
 }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            val selectedFuel = data?.getStringExtra("SELECTED_FUEL")

            when(requestCode) {
                REQUEST_FUEL_TYPE_1 -> {
                    binding.buttonConsumption1.text = selectedFuel
                }

                REQUEST_FUEL_TYPE_2 -> {
                    binding.buttonConsumption2.text = selectedFuel
                }
            }

        }
    }
}