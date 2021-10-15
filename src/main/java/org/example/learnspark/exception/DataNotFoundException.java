package org.example.learnspark.exception;

import org.eclipse.jetty.http.HttpStatus;

public class DataNotFoundException extends AbstractGeneralException {
    public DataNotFoundException(String message, Object requestData) {
        super(message, requestData, HttpStatus.NOT_FOUND_404);
    }
}
