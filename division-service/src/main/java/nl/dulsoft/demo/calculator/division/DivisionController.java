package nl.dulsoft.demo.calculator.division;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/divide", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DivisionController {

    @RequestMapping(value = "/{left}/{right}")
    public Integer add(@PathVariable(name = "left") Integer left,
                       @PathVariable(name = "right") Integer right) {
        if (right == 0) {
            throw new DivisionByZeroException();
        }
        return left / right;
    }
}
