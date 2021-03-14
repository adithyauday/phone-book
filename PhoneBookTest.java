package comp9103;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PhoneBookTest {

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
	public void addPhoneContactValidContact() {
		PhoneBook testPhoneBook = new PhoneBook();
		testPhoneBook.addPhoneContact(validPhoneContact);
		assertEquals(testPhoneBook.getPhoneBook().size(), 1);		
	}
	
	@Test
	public void addPhoneContactInvalidContact() {
		PhoneBook testPhoneBook = new PhoneBook();
		testPhoneBook.addPhoneContact(invalidPhoneContact);
		assertEquals(testPhoneBook.getPhoneBook().size(), 0);		
	}
	
	@Test
	public void removePhoneContactValidContact() {
		PhoneBook testPhoneBook = new PhoneBook();
		testPhoneBook.addPhoneContact(validPhoneContact);
		assertEquals(testPhoneBook.getPhoneBook().size(), 1);	
		testPhoneBook.removePhoneContact(validPhoneContact.getName()+";"+validPhoneContact.getBirthday());
		assertEquals(testPhoneBook.getPhoneBook().size(), 0);		
	}
	
	@Test
	public void queryPhoneContactsValidQuery() {
		PhoneBook testPhoneBook = new PhoneBook();
		testPhoneBook.addPhoneContact(validPhoneContact);
		assertEquals(testPhoneBook.getPhoneBook().size(), 1);	
		String instruction = "phone 13649852";
		testPhoneBook.queryPhoneContacts(instruction);
		PhoneBook queryPhoneBook = (PhoneBook) testPhoneBook.getQueryPhoneBookList().keySet().toArray()[0];
		assertEquals(queryPhoneBook.getPhoneBook().size(),1);
	}
	
	@Test
	public void queryPhoneContactsInvalidQuery() {
		PhoneBook testPhoneBook = new PhoneBook();
		testPhoneBook.addPhoneContact(validPhoneContact);
		assertEquals(testPhoneBook.getPhoneBook().size(), 1);	
		String instruction = "phone 13649853";
		testPhoneBook.queryPhoneContacts(instruction);
		PhoneBook queryPhoneBook = (PhoneBook) testPhoneBook.getQueryPhoneBookList().keySet().toArray()[0];
		assertEquals(queryPhoneBook.getPhoneBook().size(),0);	
	}
}
