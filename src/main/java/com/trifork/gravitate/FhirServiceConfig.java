package com.trifork.gravitate;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FhirServiceConfig {

    @Bean
    public ServletRegistrationBean fhirServlet(FhirService fhirService) {
        return new ServletRegistrationBean(fhirService, "/fhir/*");
    }
}
