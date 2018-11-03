package nl.dulsoft.demo.calculator.deduction;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DeductionService {

    @RequestMapping(value = "/{left}/{right}")
    public Integer deduct(@PathVariable(name = "left") Integer left,
                          @PathVariable(name = "right") Integer right) {
        return left - right;
    }
}
