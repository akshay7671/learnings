package com.samples.test.junit_mockito;

import com.samples.test.junit_mockito.exception.ContactValidationFailedException;
import com.samples.test.junit_mockito.service.ContactManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {
    private ContactManager contactManager = null;

    @BeforeAll
     void initialSetup() {
        System.out.println("This method is executed before all");
    }

    @BeforeEach
    void createContactManager() {
        contactManager = new ContactManager();
        System.out.println("contact manager created");

    }

    @Test
    void shouldCreateContact() throws ContactValidationFailedException {
        contactManager.addContact("Akshay", "Jadhav", "0959590111");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should not create contact when first name is null")
    void shouldThrowRuntimeWhenFirstNameIsNull() {
        assertThrows(ContactValidationFailedException.class, () -> contactManager.addContact(null, "Jadhav", "0959590111"));
    }

    @Test
    @DisplayName("Should not create contact when last name is null")
    void shouldThrowRuntimeWhenLastNameIsNull() {
        assertThrows(ContactValidationFailedException.class, () -> contactManager.addContact("Akshay", null, "0959590111"));

    }

    @Test
    @DisplayName("Should not create contact when phonenumber is null")
    void shouldThrowRuntimeWhenPhNumIsNull() {
        assertThrows(ContactValidationFailedException.class, () -> contactManager.addContact("Akshay", "Jadhav", null));
    }

    @AfterEach
    void tearDown() {
        contactManager = null;
        System.out.println("contact manager cleared");
    }

    @AfterAll
    void clearAllResources() {
        System.out.println("This method is executed after all");
    }
}