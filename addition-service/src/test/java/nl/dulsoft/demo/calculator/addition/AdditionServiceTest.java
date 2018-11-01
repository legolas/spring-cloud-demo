package nl.dulsoft.demo.calculator.addition;

import org.junit.Test;

import static org.junit.Assert.*;

public class AdditionServiceTest {

    private AdditionService additionServiceUnderTest = new AdditionService();

    @Test
    public void itShouldCreate() {
        assertNotNull(additionServiceUnderTest);
    }
}
