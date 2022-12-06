package com.trifork.gravitate;


import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.RestfulServer;
import ca.uhn.fhir.rest.server.interceptor.LoggingInterceptor;
import ca.uhn.fhir.rest.server.interceptor.ResponseHighlighterInterceptor;
import org.springframework.stereotype.Service;


@Service
public class FhirService extends RestfulServer {
    public FhirService(LensProvider lensResourceProvider) {
        super(FhirContext.forR4());

        registerProvider(lensResourceProvider);
        registerInterceptor(new ResponseHighlighterInterceptor());
        registerInterceptor(new LoggingInterceptor());

    }

}