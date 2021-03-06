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

import javax.annotation.PostConstruct;
import java.util.Optional;

@RefreshScope
@RestController
@RequestMapping(value = "/fibonacci", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FibonacciController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FibonacciController.class);

    @Value("${addition-service-url}")
    private String additionServiceUrl;

    private AdditionServiceProxy additionService;

    @Autowired
    public FibonacciController(AdditionServiceProxy additionService) {
        this.additionService = additionService;
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
        int newTerm = term - 1;
        int nextValue = additionService.add(val, prev);

        return calculateFib(newTerm, nextValue, val);
    }
}
