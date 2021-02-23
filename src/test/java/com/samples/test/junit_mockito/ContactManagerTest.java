package com.samples.test.junit_mockito;

import com.samples.test.junit_mockito.exception.ContactValidationFailedException;
import com.samples.test.junit_mockito.service.ContactManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactManagerTest {

    @Test
    public void shouldCreateContact() throws ContactValidationFailedException {
        ContactManager contactManager = new ContactManager();
        contactManager.addContact("Akshay", "Jadhav", "0959590111");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should not create contact when first name is null")
    public void shouldThrowRuntimeWhenFirstNameIsNull() {
        ContactManager contactManager = new ContactManager();
        assertThrows(ContactValidationFailedException.class, () -> contactManager.addContact(null, "Jadhav", "0959590111"));
    }

    @Test
    @DisplayName("Should not create contact when last name is null")
    public void shouldThrowRuntimeWhenLastNameIsNull() {
        ContactManager contactManager = new ContactManager();
        assertThrows(ContactValidationFailedException.class, () -> contactManager.addContact("Akshay", null, "0959590111"));

    }

    @Test
    @DisplayName("Should not create contact when phonenumber is null")
    public void shouldThrowRuntimeWhenPhNumIsNull() {
        ContactManager contactManager = new ContactManager();
        assertThrows(ContactValidationFailedException.class, () -> contactManager.addContact("Akshay", "Jadhav", null));

    }

}