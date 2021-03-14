package comp9103;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PhoneContactTest {
	PhoneContact validPhoneContact;
	PhoneContact invalidPhoneContact;
	
	@Before
    public void setUp() throws Exception {
		validPhoneContact = new PhoneContact();
		validPhoneContact.setName("John Jones");
		validPhoneContact.setBirthday("18-09-1968");
		validPhoneContact.setaddress("2 Fairmount St, Dulwich Hill, NSW 2203");
		validPhoneContact.setPhoneNumber("13649852");
		invalidPhoneContact = new PhoneContact();
		invalidPhoneContact.setaddress("2 Fairmount St, Dulwich Hill, NSW 2203");
		invalidPhoneContact.setPhoneNumber("13649852");
    }
	
	@Test
	public void constructorPhoneContactValidParams() {
		String params = "name Jo Bloggs; birthday 08-07-1900; phone 88884444; email jobloggs@gmail.com; address 9001 Chester Crescent, Chatswood, NSW 2057 ";
		PhoneContact testPhoneContact = new PhoneContact(params);	
		
		assertEquals(testPhoneContact.getName(), "Jo Bloggs");	
		assertEquals(testPhoneContact.getBirthday(), "08-07-1900");
		assertEquals(testPhoneContact.getPhoneNumber(), "88884444");
		assertEquals(testPhoneContact.getEmail(),"jobloggs@gmail.com");
		assertEquals(testPhoneContact.getAddress(), "9001 Chester Crescent, Chatswood, NSW 2057");
		
	}
	
	@Test
	public void isValidToAddValidContact() {
		assertTrue(validPhoneContact.isValidToAdd());
	}
	
	@Test
	public void isValidToAddInvalidContact() {
		assertFalse(invalidPhoneContact.isValidToAdd());
	}
	
	@Test
	public void isValidToAddInvalidContactInvalidName() {
		validPhoneContact.setName("10");
		assertFalse(validPhoneContact.isValidToAdd());
	}
	
	@Test
	public void isValidToAddInvalidContactInvalidBirthday() {
		validPhoneContact.setBirthday("13333-33");;
		assertFalse(validPhoneContact.isValidToAdd());
	}
	
	@Test
	public void isValidToAddInvalidContactInvalidPhoneNumber() {
		validPhoneContact.setPhoneNumber("9999a99");
		assertTrue(validPhoneContact.isValidToAdd());
		assertEquals(validPhoneContact.getPhoneNumber(), null);
	}
	
	@Test
	public void isValidToAddInvalidContactInvalidEmail() {
		validPhoneContact.setEmail("9999a99");
		assertTrue(validPhoneContact.isValidToAdd());
		assertEquals(validPhoneContact.getEmail(), null);
	}
	
	@Test
	public void isValidToAddInvalidContactInvalidAddress() {
		validPhoneContact.setaddress("9999a99");
		assertTrue(validPhoneContact.isValidToAdd());
		assertEquals(validPhoneContact.getAddress(), null);
	}
}

