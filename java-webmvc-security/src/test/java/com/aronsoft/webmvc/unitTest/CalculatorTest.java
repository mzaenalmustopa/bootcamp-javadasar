package com.aronsoft.webmvc.unitTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private Calculator calculator = new Calculator();

    @Test
    void testDivideFailed() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10,0);
        });
    }

    @Test
    void testDivideSuccess() {
        int result = calculator.divide(200,10);
        assertEquals(20,result);
        assertNotEquals(10,result);
    }

    @Test
    void testAdd() {
        int result = calculator.add(10,10);

        assertNotNull(result);
        assertEquals(20, result);
    }

    @Test
    void testMultiple() {
        int result = calculator.multiple(10,20);
        assertNotNull(result);
        assertNotEquals(100,result);
    }
}