package nl.dulsoft.demo.calculator.calculator;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdditionApplicationTests {

    @MockBean
    private BuildProperties buildPropertiesMock;

    @Test
    public void contextLoads() {
    }
}
