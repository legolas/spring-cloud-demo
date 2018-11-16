package nl.dulsoft.demo.calculator.addition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdditionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdditionController.class);

    @RequestMapping(method = RequestMethod.GET)
    public Link describe() {
        return linkTo(this.getClass())
                .withRel("next")
                .withSelfRel();
    }

    @RequestMapping(value = "/{left}/{right}", method = RequestMethod.GET)
    public Integer add(@PathVariable("left") Integer left,
                       @PathVariable("right") Integer right) {
        LOGGER.info("Add {} to {}", right, left);
        return left + right;
    }
}
