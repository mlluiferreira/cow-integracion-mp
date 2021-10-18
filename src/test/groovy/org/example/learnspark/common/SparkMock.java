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

    public static Request mockRequestWithMethodAndPathInfo(String method, String pathInfo) {
        Request request = Mockito.mock(Request.class);
        Mockito.when(request.pathInfo()).thenReturn(pathInfo);
        Mockito.when(request.requestMethod()).thenReturn(method);
        return request;
    }

    public static Request mockParam(String key, String value) {
        Request request = Mockito.mock(Request.class);
        Mockito.when(request.params(key)).thenReturn(value);
        return request;
    }

    public static Response mockResponseRedirect() {
        Response response = Mockito.mock(Response.class);
        Mockito.doNothing().when(response).redirect(Mockito.any());
        return response;
    }

    public static Response mockResponse() {
        Response response = Mockito.mock(Response.class);
        Mockito.doNothing().when(response).body(Mockito.any());
        Mockito.doNothing().when(response).status(Mockito.anyInt());
        return response;
    }

    public static Response mockResponseWithException(Class<Exception> exception) {
        Response response = Mockito.mock(Response.class);
        Mockito.doThrow(exception).when(response).body(Mockito.any());
        return response;
    }
}
