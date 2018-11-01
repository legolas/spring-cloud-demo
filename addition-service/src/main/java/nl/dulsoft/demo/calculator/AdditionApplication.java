package nl.dulsoft.demo.calculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdditionApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdditionApplication.class);

    private BuildProperties buildProperties;

    AdditionApplication(@Autowired BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    public static void main(String[] args) {
        SpringApplication.run(AdditionApplication.class, args);
    }

    @Bean
    public String buildInfo() {
        String buildInfo = String.format("%s-%s build on %s",
                buildProperties.getName(),
                buildProperties.getVersion(),
                buildProperties.getTime());
        LOGGER.info(buildInfo);
        return buildInfo;
    }
}
