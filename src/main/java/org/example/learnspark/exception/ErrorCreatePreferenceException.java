package org.example.learnspark.exception;

import org.eclipse.jetty.http.HttpStatus;

public class ErrorCreatePreferenceException extends AbstractGeneralException {
    public ErrorCreatePreferenceException(String message, Object requestData) {
        super(message, requestData, HttpStatus.BAD_REQUEST_400);
    }
}
