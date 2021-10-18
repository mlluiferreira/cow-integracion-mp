package org.example.learnspark.common;

import org.mockito.Mockito;
import spark.Request;
import spark.Response;

import java.io.IOException;

public class SparkMock {

    public static Request mockRequest(String path) throws IOException {
        Request request = Mockito.mock(Request.class);
        Mockito.when(request.body()).thenReturn(FileUtils.loadFileAsString(path));
        return request;
    }

    public static Request mockRequest() {
        Request request = Mockito.mock(Request.class);
        Mockito.when(request.body()).thenReturn("");
        return request;
    }

    public static Request mockParam(String key, String value) {
        Request request = Mockito.mock(Request.class);
        Mockito.when(request.params(key)).thenReturn(value);
        return request;
    }

    public static Response mockResponse() {
        return Mockito.mock(Response.class);
    }
}
