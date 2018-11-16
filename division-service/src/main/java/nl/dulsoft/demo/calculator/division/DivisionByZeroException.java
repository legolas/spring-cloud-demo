package nl.dulsoft.demo.calculator.division;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException() {
        super("Arithmic error division by zero");
    }
}
