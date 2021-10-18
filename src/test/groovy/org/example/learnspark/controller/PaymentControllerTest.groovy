package org.example.learnspark.controller

import org.example.learnspark.common.BaseMPTest
import org.example.learnspark.common.SparkMock
import spark.Request
import spark.Response;

class PaymentControllerTest extends BaseMPTest {
    def "should create an payment"() {
        given:
            Request request = SparkMock.mockRequest("payload_create_payment.json");
            Response response = SparkMock.mockResponse();
            dummyHttpClient.mock(200, "payment_mock_mp_response.json")
        when:
            PaymentController.createPayment(request, response)
        then:
            noExceptionThrown()
    }

    def "should find payment by id"() {
        given:
            Request request = SparkMock.mockParam("id", "1");
            Response response = SparkMock.mockResponse();
            dummyHttpClient.mock(200, "payment_mock_mp_response.json")
        when:
            PaymentController.getPaymentById(request, response)
        then:
            noExceptionThrown()
    }

    def "should redirect to payment - ticket"() {
        given:
            Request request = SparkMock.mockParam("id", "1");
            Response response = SparkMock.mockResponseRedirect();
            dummyHttpClient.mock(200, file)
        when:
            PaymentController.payPaymentById(request, response)
        then:
            noExceptionThrown()
        where:
            file                                     | _
            "payment_mock_mp_response.json"         | _
            "payment_pix_mock_mp_response.json"     | _
    }
}
