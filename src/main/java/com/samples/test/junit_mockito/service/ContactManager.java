package com.samples.test.junit_mockito.service;

import com.samples.test.junit_mockito.exception.ContactValidationFailedException;
import com.samples.test.junit_mockito.model.Contact;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactManager {

    Map<String, Contact> contactList = new ConcurrentHashMap<String, Contact>();

    public void addContact(String firstName, String lastName, String phoneNumber) throws ContactValidationFailedException {
        Contact contact = new Contact(firstName, lastName, phoneNumber);
        validateContact(contact);
        checkIfContactAlreadyExist(contact);
        contactList.put(generateKey(contact), contact);
    }

    public Collection<Contact> getAllContacts() {
        return contactList.values();
    }

    private void checkIfContactAlreadyExist(Contact contact) throws ContactValidationFailedException {
        if (contactList.containsKey(generateKey(contact)))
            throw new ContactValidationFailedException("Contact Already Exists");
    }

    private void validateContact(Contact contact) throws ContactValidationFailedException {
        contact.validateFirstName();
        contact.validateLastName();
        contact.validatePhoneNumber();
    }

    private String generateKey(Contact contact) {
        return String.format("%s-%s", contact.getFirstName(), contact.getLastName());
    }
}