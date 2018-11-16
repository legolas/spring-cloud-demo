package nl.dulsoft.demo.calculator.deduction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeductionControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private DeductionController deductionControllerUnderTest;

    @Before
    public void initialise() throws Exception {
        mockMvc = standaloneSetup(this.deductionControllerUnderTest).build();
    }

    @Test
    public void itShouldDeductTwoValues() throws Exception {
        mockMvc.perform(get("/9/3").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> expectToEqual(result, "6"));
    }

    @Test
    public void itShouldDeductTwoValuesWithOneNegative() throws Exception {
        mockMvc.perform(get("/9/-3").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> expectToEqual(result, "12"));
    }

    private void expectToEqual(MvcResult result, String expected) throws UnsupportedEncodingException {
        assertEquals(expected, result.getResponse().getContentAsString());
    }
}
