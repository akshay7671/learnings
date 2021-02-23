package com.samples.test.junit_mockito.model;

import com.samples.test.junit_mockito.exception.ContactValidationFailedException;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void validateFirstName() throws ContactValidationFailedException {
        if (this.firstName == null)
            throw new ContactValidationFailedException("First Name Cannot be blank");
    }

    public void validateLastName() throws ContactValidationFailedException {
        if (this.lastName == null)
            throw new ContactValidationFailedException("Last Name Cannot be blank");
    }

    public void validatePhoneNumber() throws ContactValidationFailedException {
        if (this.phoneNumber == null)
            throw new ContactValidationFailedException("Phone Number Cannot be blank");

        if (this.phoneNumber.length() != 10) {
            throw new ContactValidationFailedException("Phone Number Should be 10 Digits Long");
        }
        if (!this.phoneNumber.matches("\\d+")) {
            throw new ContactValidationFailedException("Phone Number Contain only digits");
        }
        if (!this.phoneNumber.startsWith("0")) {
            throw new ContactValidationFailedException("Phone Number Should Start with 0");
        }
    }
}