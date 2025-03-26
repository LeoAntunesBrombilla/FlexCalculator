package com.countup.flexcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.countup.flexcalculator.databinding.ActivityGasOptionsBinding

class GasOptions : AppCompatActivity() {

    private lateinit var binding: ActivityGasOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGasOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fuelTypes = resources.getStringArray(R.array.fuel_types)


        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            fuelTypes
        )

        binding.listViewFuelOptions.adapter = adapter

        binding.listViewFuelOptions.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedFuel = fuelTypes[position]

            val resultIntent = Intent()
            resultIntent.putExtra("SELECTED_FUEL", selectedFuel)
            setResult(RESULT_OK, resultIntent)

            finish()
        }
    }
}