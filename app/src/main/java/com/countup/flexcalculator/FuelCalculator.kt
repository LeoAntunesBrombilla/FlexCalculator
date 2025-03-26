package com.countup.flexcalculator

fun fuelCalculator(quantityFuel1: Double, priceFuel1: Double, quantityFuel2: Double, priceFuel2: Double): Int {
    val result1 = quantityFuel1 * priceFuel1
    val result2 = quantityFuel2 * priceFuel2

    return if(result1 < result2) 1 else 2
}
