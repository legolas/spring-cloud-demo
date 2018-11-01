package nl.dulsoft.demo.calculator.fibonacci;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class FibonacciService {

    @RequestMapping(value = "/{max}")
    public int fibonacci(@PathVariable(name = "max") int max) {
        return calulateFib(max, 1, 0);
    }

    private int calulateFib(int term, int val, int prev) {
        if (term == 0) return prev;
        return calulateFib(term - 1, val + prev, val);
    }
}
