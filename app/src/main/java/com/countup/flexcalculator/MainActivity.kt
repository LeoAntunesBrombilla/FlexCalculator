package com.countup.flexcalculator

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
            binding.textViewConsumption1.text = "Consumo de $selectedFuel:"
            binding.tvGasPrice1.text = "Valor litro $selectedFuel:"
        }
    }

    private val fuelLauncher2 = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
            result -> if(result.resultCode == RESULT_OK) {
            val selectedFuel = result.data?.getStringExtra("SELECTED_FUEL")
            binding.textViewConsumption.text = "Consumo de $selectedFuel:"
            binding.tvTextPrice2.text = "Valor litro $selectedFuel:"
        }
    }



    private fun fuelResultBinding(quantityFuel1: Double, priceFuel1: Double, quantityFuel2: Double, priceFuel2: Double): CharSequence? {
        val cheaperFuel = fuelCalculator(quantityFuel1, priceFuel1, quantityFuel2, priceFuel2)
        return if(cheaperFuel == 1) {
            return binding.textViewConsumption1.text.toString()
        } else {
            return binding.textViewConsumption.text.toString()
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

        setupWatchers()
 }

    private fun setupWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                checkAllFieldsAndCalculate()
            }
        }

        binding.textViewConsumption1.addTextChangedListener(textWatcher)
        binding.textViewConsumption.addTextChangedListener(textWatcher)
        binding.editTextConsumption1.addTextChangedListener(textWatcher)
        binding.editTextConsumption.addTextChangedListener(textWatcher)
        binding.etGasPrice1.addTextChangedListener(textWatcher)
        binding.etGasPrice2.addTextChangedListener(textWatcher)
    }

    private fun checkAllFieldsAndCalculate() {
        val quantityFuel1 = binding.editTextConsumption1.text.toString()
        val priceFuel1 = binding.editTextConsumption1.text.toString()
        val quantityFuel2 = binding.editTextConsumption.text.toString()
        val priceFuel2 = binding.editTextConsumption.text.toString()
        val gasPrice1 = binding.etGasPrice1.text.toString()
        val gasPrice2 = binding.etGasPrice2.text.toString()

        if(quantityFuel1.isNotEmpty() && priceFuel1.isNotEmpty()
            && quantityFuel2.isNotEmpty() && priceFuel2.isNotEmpty()
            && gasPrice1.isNotEmpty() && gasPrice2.isNotEmpty()
        ) {

            val quantity1 = quantityFuel1.toDoubleOrNull() ?: return
            val priceText1 = priceFuel1.toDoubleOrNull() ?: return
            val quantity2 = quantityFuel2.toDoubleOrNull() ?: return
            val priceText2 = priceFuel2.toDoubleOrNull() ?: return
            val gasPrice1 = gasPrice1.toDoubleOrNull() ?: return
            val gasPrice2 = gasPrice2.toDoubleOrNull() ?: return

            val result = fuelResultBinding(
                quantity1, gasPrice1, quantity2, gasPrice2
            )

            binding.etResult.setText(result)
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