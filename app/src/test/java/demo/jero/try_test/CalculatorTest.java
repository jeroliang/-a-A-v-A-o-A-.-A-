package demo.jero.try_test;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jero on 2018/3/5 0005.
 */
public class CalculatorTest {
    @Test
    public void sum() throws Exception {
    }

    @Test
    public void substract() throws Exception {
    }

    @Test
    public void divide() throws Exception {
    }

    @Test
    public void multiply() throws Exception {
    }
    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @Test
    public void testSum() throws Exception {
        //expected: 6, sum of 1 and 5
        assertEquals(6d, mCalculator.sum(1d, 5d), 0);
        assertEquals(7d, mCalculator.sum(2d, 5d), 0);
    }

    @Test
    public void testSum1() throws Exception {
        //expected: 6, sum of 1 and 5
        assertEquals("1+5=6",6d, mCalculator.sum(1d, 5d), 0);
    }

    @Test
    public void testSubstract() throws Exception {
        assertEquals(1d, mCalculator.substract(5d, 4d), 0);
    }

    @Test
    public void testDivide() throws Exception {
        assertEquals(4, mCalculator.divide(20d, 5d), 0);
    }

    @Test
    public void testMultiply() throws Exception {
        assertEquals(10d, mCalculator.multiply(2d, 5d), 0);
    }


}