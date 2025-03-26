package com.countup.flexcalculator

import org.junit.Test

import org.junit.Assert.*

class MainActivityUnitTest {

    @Test
    fun `fuelCalculator retorna o consumo correto baseado no calculo`(){
        assertEquals(1 , fuelCalculator(10.0, 1.5,10.0,2.0))
        assertEquals(2 , fuelCalculator(10.0, 2.5,10.0,1.5))
        assertEquals(2 , fuelCalculator(10.0, 2.0,10.0,2.0))
    }
}