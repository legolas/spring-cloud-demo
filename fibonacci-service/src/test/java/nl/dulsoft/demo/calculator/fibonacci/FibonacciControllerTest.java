package nl.dulsoft.demo.calculator.fibonacci;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FibonacciControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private FibonacciController fibonacciController;

    @Value("${addition-service-url}")
    private String baseUrl;

    @Before
    public void initialise() {
        mockMvc = standaloneSetup(this.fibonacciController).build();

        when(restTemplate.getForObject(baseUrl + "/1/0", Integer.class)).thenReturn(1);
        when(restTemplate.getForObject(baseUrl + "/1/1", Integer.class)).thenReturn(2);
        when(restTemplate.getForObject(baseUrl + "/2/1", Integer.class)).thenReturn(3);
        when(restTemplate.getForObject(baseUrl + "/3/2", Integer.class)).thenReturn(5);
        when(restTemplate.getForObject(baseUrl + "/5/3", Integer.class)).thenReturn(8);
    }

    @Test
    public void itShouldCalculateFibonacciFor5() throws Exception {
        mockMvc.perform(get("/5").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(result -> expectToEqual(result, "5"));
    }

    private void expectToEqual(MvcResult result, String expected) throws UnsupportedEncodingException {
        assertEquals(expected, result.getResponse().getContentAsString());
    }

    @Test
    public void itShouldReturn0For0() throws Exception {
        mockMvc.perform(get("/0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> expectToEqual(result, "0"));
    }

    @Test
    public void itShouldReturn0ForLessThan0() throws Exception {
        mockMvc.perform(get("/-1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> expectToEqual(result, "0"));
    }
}
