package org.example.learnspark.common

import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpRequestBase
import org.apache.http.protocol.HttpContext
import org.apache.http.util.EntityUtils
import org.mockito.Mockito

class DummyHttpClientTest extends BaseMPTest {
    def "should mock response with success"() {
        given:
            dummyHttpClient.mock(statusCode, "test.txt")
            HttpRequestBase requestBase = Mockito.mock(HttpRequestBase.class)
            HttpContext httpContext = Mockito.mock(HttpContext.class)
        when:
            HttpResponse response = dummyHttpClient.execute(requestBase, httpContext)
        then:
            response.getStatusLine().getStatusCode() == statusCode
            EntityUtils.toString(response.getEntity()) == expectedBody
        where:
            statusCode | expectedBody
            200        | "test\n"
    }
}
