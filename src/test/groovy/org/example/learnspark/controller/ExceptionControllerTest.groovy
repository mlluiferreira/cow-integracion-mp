package org.example.learnspark.controller

import org.example.learnspark.common.BaseMPTest
import org.example.learnspark.common.SparkMock
import org.example.learnspark.exception.AbstractGeneralException
import org.example.learnspark.model.ErrorInfoDTO
import spark.Request
import spark.Response;

class ExceptionControllerTest extends BaseMPTest {
    def "should return an error info"() {
        given:
            AbstractGeneralException abstractGeneralException = new AbstractGeneralException("message", "", 400);
            Request request = SparkMock.mockRequestWithMethodAndPathInfo("GET", "/any")
            Response response = SparkMock.mockResponse()
        when:
            ExceptionController.handler(abstractGeneralException, request, response)
        then:
            noExceptionThrown()
    }

    def "should return error info if occur error during error info parser"() {
        given:
            AbstractGeneralException abstractGeneralException = new AbstractGeneralException("message", "", 400);
            Request request = SparkMock.mockRequestWithMethodAndPathInfo("GET", "/any")
            Response response = SparkMock.mockResponseWithException(RuntimeException.class)
        when:
            ErrorInfoDTO result = ExceptionController.handler(abstractGeneralException, request, response)
        then:
            result != null
    }
}
