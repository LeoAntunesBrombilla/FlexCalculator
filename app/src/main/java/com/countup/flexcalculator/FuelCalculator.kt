package com.countup.flexcalculator

fun fuelCalculator(quantityFuel1: Double, priceFuel1: Double, quantityFuel2: Double, priceFuel2: Double): Int {
    val totalCost1 = quantityFuel1 * priceFuel1
    val totalCost2 = quantityFuel2 * priceFuel2

    return when {
        totalCost1 < totalCost2 -> 1
        totalCost1 > totalCost2 -> 2
        else -> 0
    }
}
