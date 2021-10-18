package org.example.learnspark.controller;

import org.example.learnspark.common.BaseMPTest
import org.example.learnspark.common.SparkMock
import spark.Request
import spark.Response;

class PaymentMethodControllerTest extends BaseMPTest {
    def "should return all payment methods"() {
        given:
            Request request = SparkMock.mockRequest()
            Response response = SparkMock.mockResponse();
            dummyHttpClient.mock(200, "get_all_payment_methods_mock_mp_response.json")
        when:
            PaymentMethodController.getPaymentMethods(request, response)
        then:
            noExceptionThrown()
    }

    def "should return payment method by type"() {
        given:
            Request request = SparkMock.mockParam("type", "ticket")
            Response response = SparkMock.mockResponse();
            dummyHttpClient.mock(200, "get_all_payment_methods_mock_mp_response.json")
        when:
            PaymentMethodController.getPaymentMethodsByType(request, response)
        then:
            noExceptionThrown()
    }
}
