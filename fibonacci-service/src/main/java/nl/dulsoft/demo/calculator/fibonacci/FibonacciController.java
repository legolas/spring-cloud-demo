package nl.dulsoft.demo.calculator.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Optional;

@RefreshScope
@RestController
@RequestMapping(value = "/fibonacci", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FibonacciController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciController.class);
    @Value("${addition-service-url}")
    private String additionServiceUrl;

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
        return calculateFib(Optional.of(max)
                        .filter(value -> value >= 0)
                        .orElse(0),
                1, 0);
    }

    private int calculateFib(int term, int val, int prev) {
        if (term == 0) return prev;
        return calculateFib(term - 1, add(val, prev), val);
    }

    private Integer add(int left, int right) {
        String uri = String.format("%s/%d/%d", additionServiceUrl, left, right);

        LOGGER.info("Calling add service at: {}", uri);
        return restTemplate.getForObject(uri, Integer.class);
    }
}
