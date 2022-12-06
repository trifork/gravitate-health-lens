package com.trifork.gravitate;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.annotation.Operation;
import ca.uhn.fhir.rest.annotation.OperationParam;
import ca.uhn.fhir.util.BundleUtil;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Medication;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.Parameters;
import org.springframework.stereotype.Service;


@Service
public class LensProvider {

    public LensProvider() {
    }


    @Operation(name = "focus", returnParameters = {@OperationParam(name = "returnEpi", type = Bundle.class), @OperationParam(name = "returnEpiExtracts", type = Bundle.class)})
    public Parameters getFocus(@OperationParam(name = "ips", min = 1, max = 1) Bundle ips, @OperationParam(name = "epi", min = 1, max = 1) Bundle epi) {
        var medicationStatements = BundleUtil.toListOfResourcesOfType(FhirContext.forR4(), ips, MedicationStatement.class);
        var medications = BundleUtil.toListOfResourcesOfType(FhirContext.forR4(), ips, Medication.class);
        var epiIdentifier = epi.getIdentifier();

        //Do the heavy computation and return it in the Parameters
        var highLigthedEpi = new Bundle();
        var highLigthedEpiExtracts = new Bundle();

        return new Parameters().addParameter(new Parameters.ParametersParameterComponent().setName("returnEpi").setResource(highLigthedEpi))
                .addParameter(new Parameters.ParametersParameterComponent().setName("returnEpiExtracts").setResource(highLigthedEpiExtracts));
    }
}
