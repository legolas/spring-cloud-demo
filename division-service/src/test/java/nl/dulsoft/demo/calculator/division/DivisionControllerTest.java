package nl.dulsoft.demo.calculator.division;

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

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DivisionControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private DivisionController divisionControllerUnderTest;

    @Before
    public void initialise() throws Exception {
        mockMvc = standaloneSetup(this.divisionControllerUnderTest).build();
    }

    @Test
    public void itShouldReturn4For12DividedBy3() throws Exception {
        mockMvc.perform(get("/12/3").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> expectToEqual(result, "4"));
    }

    private void expectToEqual(MvcResult result, String expected) throws UnsupportedEncodingException {
        assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    public void itShouldReturnThrowBadRequestForDivisionByZero() throws Exception {
        mockMvc.perform(get("/12/0").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isBadRequest());
    }
}
