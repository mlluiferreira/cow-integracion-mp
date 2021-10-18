package org.example.learnspark.services

import com.mercadopago.resources.IdentificationType
import org.example.learnspark.common.BaseMPTest;

class IdentificationServiceTest extends BaseMPTest {

    def setup() {
        IdentificationService.clearCache();
    }

    def "should retrieve all identification types"() {
        given:
            dummyHttpClient.mock(200, "get_identification_type_mock_mp_response.json")
        when:
            List<IdentificationType> identificationTypes = IdentificationService.getIdentificationTypes();
        then:
            identificationTypes != null
            identificationTypes.size() == 2
    }

    def "should retrieve all identification types using cache"() {
        given:
        dummyHttpClient.mock(200, "get_identification_type_mock_mp_response.json")
        when:
            IdentificationService.getIdentificationTypes();
            List<IdentificationType> identificationTypes = IdentificationService.getIdentificationTypes();
        then:
        identificationTypes != null
        identificationTypes.size() == 2
    }

    def "should return empty if cant retrieve identification types"() {
        given:
            dummyHttpClient.mock(RuntimeException.class)
        when:
            List<IdentificationType> identificationTypes = IdentificationService.getIdentificationTypes();
        then:
            identificationTypes != null
            identificationTypes.size() == 0
    }

    def "should retrieve identification types by id"() {
        given:
            dummyHttpClient.mock(200, "get_identification_type_mock_mp_response.json")
        when:
            List<IdentificationType> identificationTypes = IdentificationService.getIdentificationById(identificationType);
        then:
            identificationTypes != null
            identificationTypes.size() == 1
            identificationTypes.get(0).getId() == id
        where:
            identificationType  | id
            "cpf"              | "CPF"
            "CPF"              | "CPF"
            "cnpj"             | "CNPJ"
            "CNPJ"             | "CNPJ"
    }

    def "should retrieve identification types by id using cache"() {
        given:
            dummyHttpClient.mock(200, "get_identification_type_mock_mp_response.json")
        when:
            IdentificationService.getIdentificationById(identificationType)
            List<IdentificationType> identificationTypes = IdentificationService.getIdentificationById(identificationType);
        then:
            identificationTypes != null
            identificationTypes.size() == 1
            identificationTypes.get(0).getId() == id
        where:
            identificationType  | id
            "cpf"              | "CPF"
            "CPF"              | "CPF"
            "cnpj"             | "CNPJ"
            "CNPJ"             | "CNPJ"
    }

    def "should retrieve empty if not found identification type"() {
        given:
            dummyHttpClient.mock(200, "get_identification_type_mock_mp_response.json")
        when:
            List<IdentificationType> identificationTypes = IdentificationService.getIdentificationById("non_exist");
        then:
            identificationTypes != null
            identificationTypes.size() == 0
    }
}
