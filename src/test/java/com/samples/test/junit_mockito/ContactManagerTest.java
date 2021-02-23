package com.samples.test.junit_mockito;

import com.samples.test.junit_mockito.exception.ContactValidationFailedException;
import com.samples.test.junit_mockito.service.ContactManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContactManagerTest {
    private ContactManager contactManager = null;

    private static List<String> phoneNumberList() {
        return Arrays.asList("0123456789", "1234567890");
    }

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

    @Test
    @DisplayName("Should create contact on mac only")
    @EnabledOnOs(value = OS.MAC)
    void shouldCreateContactOnlyOnMac() throws ContactValidationFailedException {
        contactManager.addContact("Akshay", "Jadhav", "0959590111");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Should create contact on windows only")
    @EnabledOnOs(value = OS.WINDOWS)
    void shouldCreateContactOnlyOnWindows() throws ContactValidationFailedException {
        contactManager.addContact("Akshay", "Jadhav", "0959590111");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @DisplayName("Assumption feature test - DEV")
    void shouldCreateContactOnAssumption() throws ContactValidationFailedException {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
        contactManager.addContact("Akshay", "Jadhav", "0959590111");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @RepeatedTest(value = 2, name = "createContact {currentRepetition} of {totalRepetitions} ")
    @DisplayName("RepeatedTest - createContact")
    void shouldCreateContactRepeatedly() throws ContactValidationFailedException {
        contactManager.addContact("Akshay", "Jadhav", "0959590111");
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @ParameterizedTest
    @DisplayName("ParameterizedTest- valueSource - testPhoneNumber while createContact")
    @ValueSource(strings = {"0123456789", "1234567890"})
    void shouldCreateContactParameterizedTest(String phoneNumber) throws ContactValidationFailedException {
        contactManager.addContact("Akshay", "Jadhav", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @ParameterizedTest
    @DisplayName("ParameterizedTest - methodSource - testPhoneNumber while createContact")
    @MethodSource("phoneNumberList")
    void shouldCreateContactParameterizedTestMethodSource(String phoneNumber) throws ContactValidationFailedException {
        contactManager.addContact("Akshay", "Jadhav", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
    }

    @ParameterizedTest
    @DisplayName("ParameterizedTest- csvSource - testPhoneNumber while createContact")
    @CsvSource({"0123456789", "0123456789"})
    void shouldCreateContactParameterizedTestCSVSource(String phoneNumber) throws ContactValidationFailedException {
        contactManager.addContact("Akshay", "Jadhav", phoneNumber);
        assertFalse(contactManager.getAllContacts().isEmpty());
        assertEquals(1, contactManager.getAllContacts().size());
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