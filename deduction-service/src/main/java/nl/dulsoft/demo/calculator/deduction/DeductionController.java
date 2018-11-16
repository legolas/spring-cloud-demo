package nl.dulsoft.demo.calculator.deduction;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DeductionController {

    @RequestMapping(value = "/{left}/{right}", method = RequestMethod.GET)
    public Integer deduct(@PathVariable(name = "left") Integer left,
                          @PathVariable(name = "right") Integer right) {
        return left - right;
    }
}
