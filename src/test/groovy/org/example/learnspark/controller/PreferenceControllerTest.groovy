package org.example.learnspark.controller

import org.example.learnspark.common.BaseMPTest
import org.example.learnspark.common.SparkMock
import spark.Request
import spark.Response

class PreferenceControllerTest extends BaseMPTest {
    def "should create an payment"() {
        given:
            Request request = SparkMock.mockRequest("payload_create_preference.json");
            Response response = SparkMock.mockResponse();
            dummyHttpClient.mock(200, "preference_mock_mp_response.json")
        when:
            PreferenceController.createPreference(request, response)
        then:
            noExceptionThrown()
    }

    def "should find payment by id"() {
        given:
            Request request = SparkMock.mockParam("id", "1");
            Response response = SparkMock.mockResponse();
            dummyHttpClient.mock(200, "preference_mock_mp_response.json")
        when:
            PreferenceController.getPreferenceById(request, response)
        then:
            noExceptionThrown()
    }

    def "should redirect to payment - ticket"() {
        given:
            Request request = SparkMock.mockParam("id", "1");
            Response response = SparkMock.mockResponseRedirect();
            dummyHttpClient.mock(200, "preference_mock_mp_response.json")
        when:
            PreferenceController.payPreferenceById(request, response)
        then:
            noExceptionThrown()
    }
}
