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

        //Pego do array de valores as options
        val fuelTypes = resources.getStringArray(R.array.fuel_types)


        //Esse adapter vai retornar uma view, pra cada elemento na lista
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            fuelTypes
        )

        binding.listViewFuelOptions.adapter = adapter

        //aqui simplesmente pegamos a position e o valor e quando selecionamos
        //ele volta a tela
        binding.listViewFuelOptions.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedFuel = fuelTypes[position]

            val resultIntent = Intent()
            //adiciona o valor
            resultIntent.putExtra("SELECTED_FUEL", selectedFuel)
            setResult(RESULT_OK, resultIntent)

            //fecha a activity
            finish()
        }
    }
}