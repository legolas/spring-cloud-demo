package nl.dulsoft.demo.calculator.fibonacci;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/*
int fib(int term, int val = 1, int prev = 0)
{
 if(term == 0) return prev;
 return fib(term - 1, val+prev, val);
}
 */

@RunWith(MockitoJUnitRunner.class)
public class FibonacciServiceTest {

    static final String DUMMY_URL = "http://dummy.url";

    private FibonacciService fibonacciServiceUnderTest;

    @Mock
    private RestTemplate restTemplateMock;

    @Before
    public void initialize() {
        fibonacciServiceUnderTest = new FibonacciService(restTemplateMock);
        fibonacciServiceUnderTest.additionServiceUrl = DUMMY_URL;

    }

    private void expect(int left, int right, int result) {
        String dummyUrl = String.format("%s/%d/%d", DUMMY_URL, left, right);
        when(restTemplateMock.getForObject(dummyUrl, Integer.class)).thenReturn(result);
    }

    @Test
    @Ignore
    public void itShouldCalculateFibonacciFor5() {
        expect(0, 1, 1);

        Integer expected = 8;
        Integer actual = fibonacciServiceUnderTest.fibonacci(6);
        assertEquals(expected, actual);
    }

    @Test
    public void itShouldReturn0For0() {
        Integer expected = 0;
        Integer actual = fibonacciServiceUnderTest.fibonacci(0);
        assertEquals(expected, actual);
    }

    @Test
    public void itShouldReturn0ForLessThan0() {
        Integer expected = 0;
        Integer actual = fibonacciServiceUnderTest.fibonacci(-1);
        assertEquals(expected, actual);
    }
}
