package com.samples.test.junit_mockito.exception;

public class ContactValidationFailedException extends Exception {
    public ContactValidationFailedException(String message) {
        super(message);
    }
}
