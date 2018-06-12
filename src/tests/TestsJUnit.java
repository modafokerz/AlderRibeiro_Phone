package tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import objects.Contact;

class TestsJUnit {

	@Test
	void testValidatePhone() {
		Contact contactTest = new Contact();
		
		contactTest.setTel("0000000000");
		assertTrue(contactTest.validatePhone());
		
		contactTest.setTel("000000000000000");
		assertFalse(contactTest.validatePhone());
		
		contactTest.setTel("000000000");
		assertFalse(contactTest.validatePhone());
		
		contactTest.setTel("0000");
		assertFalse(contactTest.validatePhone());
		
		contactTest.setTel(null);
		assertFalse(contactTest.validatePhone());
		
		contactTest.setTel("Bonjour");
		assertFalse(contactTest.validatePhone());
		
		contactTest.setTel("..........");
		assertFalse(contactTest.validatePhone());
		
		contactTest.setTel("dixLettres");
		assertFalse(contactTest.validatePhone());
		
		contactTest.setTel("W");
		assertFalse(contactTest.validatePhone());
		
		contactTest.setTel("1234567890");
		assertTrue(contactTest.validatePhone());
		
		contactTest.setTel("123 456 789 0");
		assertFalse(contactTest.validatePhone());
		
		contactTest.setTel("123 abc 45 de");
		assertFalse(contactTest.validatePhone());
		
		contactTest.setTel("123abc45de");
		assertFalse(contactTest.validatePhone());
		
	}

}
