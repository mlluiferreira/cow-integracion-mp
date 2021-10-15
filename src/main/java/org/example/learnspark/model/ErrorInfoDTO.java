package org.example.learnspark.model;

import org.example.learnspark.exception.AbstractGeneralException;
import spark.Request;

import java.util.Date;

public class ErrorInfoDTO {

    public final String url;

    public final String message;

    public final long timeStamp;

    public final String httpMethod;

    public int statusCode;

    public Object body;

    public ErrorInfoDTO(String url, String message, long timeStamp, String httpMethod, Object request, int statusCode) {
        this.url = url;
        this.message = message;
        this.timeStamp = timeStamp;
        this.httpMethod = httpMethod;
        this.body = request;
        this.statusCode = statusCode;
    }

    private ErrorInfoDTO(Request req, Exception ex) {
        this.url = req.pathInfo();
        this.httpMethod = req.requestMethod();
        this.message = ex.getMessage();
        this.timeStamp = new Date().getTime();
    }

    public void setObject(Object request) {
        this.body = request;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public static ErrorInfoDTO create(Request req, Exception ex) {
        ErrorInfoDTO errorInfo = new ErrorInfoDTO(req, ex);

        if (ex instanceof AbstractGeneralException) {
            errorInfo.setObject(((AbstractGeneralException) ex).requestData);
            errorInfo.setStatusCode(((AbstractGeneralException) ex).httpStatus);
        }

        return errorInfo;
    }

}
