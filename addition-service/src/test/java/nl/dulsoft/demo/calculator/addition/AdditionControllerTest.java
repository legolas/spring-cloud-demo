package nl.dulsoft.demo.calculator.addition;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
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
public class AdditionControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @MockBean
    private BuildProperties buildPropertiesMock;

    @Autowired
    private AdditionController additionControllerUnderTest;

    @Before
    public void initialise() {
        mockMvc = standaloneSetup(this.additionControllerUnderTest).build();
    }

    @Test
    public void itShouldAdd2Values3() throws Exception {
        mockMvc.perform(get("/add/3/3").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> expectToEqual(result, "6"));
    }

    private void expectToEqual(MvcResult result, String expected) throws UnsupportedEncodingException {
        assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    public void itShouldAddValues0And6() throws Exception {
        mockMvc.perform(get("/add/0/6").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> expectToEqual(result, "6"));
    }

    @Test
    public void itShouldAddValues9AndNegative3() throws Exception {
        mockMvc.perform(get("/add/9/-3").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> expectToEqual(result, "6"));
    }
}
