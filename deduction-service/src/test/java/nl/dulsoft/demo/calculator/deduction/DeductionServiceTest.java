package nl.dulsoft.demo.calculator.deduction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeductionServiceTest {

    private DeductionService deductionServiceUnderTest = new DeductionService();

    @Test
    public void itShouldAddTwoValues() {
        Integer left = 7;
        Integer right = 3;
        Integer expected = 4;

        assertEquals(expected, deductionServiceUnderTest.deduct(left, right));
    }
}
