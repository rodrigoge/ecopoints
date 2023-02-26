package br.com.ecopoints.userservice.configurations;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;

@TestConfiguration
public class RestTemplateConfigurationTest {

    @Test
    public void restTemplateBuilder() {
        var restTemplateBuilder = new RestTemplateBuilder();
        new RestConfiguration().restTemplate(restTemplateBuilder);
    }
}
