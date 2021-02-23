package com.samples.test.junit_mockito.controller;

import com.samples.test.junit_mockito.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MathApplicationTest {
    @InjectMocks
    private MathApplication mathApplication = new MathApplication();
    @Mock
    private CalculatorService calculatorService;


    @Test
    void add() {
        when(calculatorService.add(anyDouble(), anyDouble())).thenReturn(40.0);
        when(calculatorService.add(10, 20)).thenReturn(30.0);
        assertEquals(30.0, mathApplication.add(10.0, 20.0));
        assertEquals(40.0, mathApplication.add(20.0, 20.0));
        verify(calculatorService, times(1)).add(10, 20);
    }

    @Test
    void divideShouldFail() {
        when(calculatorService.divide(10, 0)).thenThrow(ArithmeticException.class);
        assertThrows(ArithmeticException.class, () -> mathApplication.divide(10.0, 0.0));
    }

    @Test
    void failBeforeDivide() {
        doThrow(new RuntimeException("Can't divide by 0")).when(calculatorService).divide(10, 0);
        assertThrows(RuntimeException.class, () -> mathApplication.divide(10.0, 0.0), "Can't divide by 0");
    }

    @Test
    void failBeforeDivide_2() {
        doThrow(new RuntimeException("Can't divide by 0")).when(calculatorService).divide(10, 0);
        assertThrows(RuntimeException.class, () -> mathApplication.divide(10.0, 0.0), "Can't divide by 0");
    }

    @Test
    void subtract() {
        when(calculatorService.subtract(10, 20)).thenReturn(-10.0);
        assertEquals(-10.0, mathApplication.subtract(10.0, 20.0));
        verify(calculatorService, never()).subtract(20, 20);
    }

    @Test
    void multiply() {
        when(calculatorService.multiply(10, 20)).thenReturn(200.0);
        assertEquals(200.0, mathApplication.multiply(10.0, 20.0));
    }

    @Test
    void divide() {
        when(calculatorService.divide(10, 20)).thenReturn(0.0);
        assertEquals(0.0, mathApplication.divide(10.0, 20.0));
    }


    @Test
    void inOrderDivideThenMultiply() {
        when(calculatorService.divide(20, 20)).thenReturn(1.0);
        when(calculatorService.multiply(10, 20)).thenReturn(200.0);

        assertEquals(1.0, mathApplication.divide(20.0, 20.0));
        assertEquals(200.0, mathApplication.multiply(10.0, 20.0));


        InOrder inOrder = inOrder(calculatorService);
        inOrder.verify(calculatorService).divide(20.0, 20.0);
        inOrder.verify(calculatorService).multiply(10.0, 20.0);
    }


    @Test
    void invocation() {
        when(calculatorService.subtract(10, 20)).thenAnswer((i) -> {
            System.out.println(Arrays.toString(i.getArguments()));
            return -10.0;
        });
        assertEquals(-10.0, mathApplication.subtract(10.0, 20.0));
        verify(calculatorService, times(1)).subtract(10.0, 20);
    }


    @Test
    void divideUsingSpy() {
        MathApplication mathApplication = new MathApplication();
        mathApplication.setCalculatorService(spy(new Calculator()));

        assertThrows(UnsupportedOperationException.class, () -> mathApplication.subtract(10.0, 20.0), "Method not implemented yet!\"");
    }


    @Test
    void divideAfterReset() {
        when(calculatorService.divide(10, 20)).thenReturn(0.0);
        reset(calculatorService);
        assertEquals(0.0, calculatorService.divide(10.0, 20.0));
    }


    class Calculator implements CalculatorService {
        @Override
        public double add(double input1, double input2) {
            return input1 + input2;
        }

        @Override
        public double subtract(double input1, double input2) {
            throw new UnsupportedOperationException("Method not implemented yet!");
        }

        @Override
        public double multiply(double input1, double input2) {
            throw new UnsupportedOperationException("Method not implemented yet!");
        }

        @Override
        public double divide(double input1, double input2) {
            throw new UnsupportedOperationException("Method not implemented yet!");
        }
    }
}