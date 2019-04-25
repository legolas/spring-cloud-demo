package nl.dulsoft.demo.calculator.fibonacci;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="addition-proxy", url = "localhost:8085")
public interface AdditionServiceProxy {

    @RequestMapping(value = "/add//{left}/{right}", method = RequestMethod.GET)
    Integer add(@PathVariable("left") Integer left, @PathVariable("right") Integer right);
}
