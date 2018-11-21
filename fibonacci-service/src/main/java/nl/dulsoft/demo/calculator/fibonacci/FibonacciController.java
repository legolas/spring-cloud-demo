package nl.dulsoft.demo.calculator.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FibonacciController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciController.class);
    @Value("${addition-service-url}")
    String additionServiceUrl;
    private RestTemplate restTemplate;

    @Autowired
    public FibonacciController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void printValue() {
        LOGGER.info("additionServiceUrl {}.", this.additionServiceUrl);
    }

    @RequestMapping(value = "/{max}")
    public int fibonacci(@PathVariable(name = "max") int max) {
        return calulateFib(Optional.of(max)
                        .filter(value -> value >= 0)
                        .orElse(0),
                1, 0);
    }

    private int calulateFib(int term, int val, int prev) {
        if (term == 0) return prev;
        return calulateFib(term - 1, add(val, prev), val);
    }

    private int add(int left, int right) {
        String uri = String.format("%s/%d/%d", additionServiceUrl, left, right);

        LOGGER.info("Calling add service at: {}", uri);
        Integer result = restTemplate.getForObject(uri, Integer.class);

        return result;
    }
}
