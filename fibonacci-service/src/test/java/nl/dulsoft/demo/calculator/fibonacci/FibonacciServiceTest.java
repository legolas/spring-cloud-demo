package nl.dulsoft.demo.calculator.fibonacci;

import org.junit.Test;

import static org.junit.Assert.*;

/*
int fib(int term, int val = 1, int prev = 0)
{
 if(term == 0) return prev;
 return fib(term - 1, val+prev, val);
}
 */
public class FibonacciServiceTest {

    @Test
    public void itShouldCalculateFibonacciFor5() {
        Integer expected = 8;
        Integer actual = new FibonacciService().fibonacci(6);
        assertEquals(expected, actual);
    }

    @Test
    public void itShouldReturn0For0() {
        Integer expected = 0;
        Integer actual = new FibonacciService().fibonacci(0);
        assertEquals(expected, actual);
    }
}
