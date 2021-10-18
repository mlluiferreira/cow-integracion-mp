package org.example.learnspark.controller

import org.example.learnspark.common.BaseMPTest
import org.example.learnspark.common.SparkMock
import spark.Request
import spark.Response

class IdentificationControllerTest extends BaseMPTest {
    def "should return all identification types"() {
        given:
            Request request = SparkMock.mockRequest()
            Response response = SparkMock.mockResponse();
            dummyHttpClient.mock(200, "get_identification_type_mock_mp_response.json")
            IdentificationController.getIdentificationsTypes(request, response)
        when:
            PaymentMethodController.getPaymentMethods(request, response)
        then:
            noExceptionThrown()
    }

    def "should return identification type by id"() {
        given:
            Request request = SparkMock.mockParam("id", "cpf")
            Response response = SparkMock.mockResponse();
            dummyHttpClient.mock(200, "get_identification_type_mock_mp_response.json")
        when:
            IdentificationController.getIdentificationsById(request, response)
        then:
            noExceptionThrown()
    }
}
