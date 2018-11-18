package nl.dulsoft.demo.calculator.multiplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiplyServiceTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private MultiplyService multiplyServiceUnderTest;

    @Before
    public void initialise() throws Exception {
        mockMvc = standaloneSetup(this.multiplyServiceUnderTest).build();
    }

    @Test
    public void itShouldMultiply2Values() throws Exception {
        mockMvc.perform(get("/6/6").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> expectToEqual(result, "36"));
    }

    @Test
    public void itShouldMultiplyPositiveWithNegativeValues() throws Exception {
        mockMvc.perform(get("/6/-6").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> expectToEqual(result, "-36"));
    }

    @Test
    public void itShouldMultiplyWithZero() throws Exception {
        mockMvc.perform(get("/6/0").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> expectToEqual(result, "0"));
    }

    private void expectToEqual(MvcResult result, String expected) throws UnsupportedEncodingException {
        assertEquals(expected, result.getResponse().getContentAsString());
    }
}