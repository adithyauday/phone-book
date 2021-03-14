package comp9103;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PhoneProcessorTest {
	@Test
	public void loadInputContacts4() {
		String[] params = {"contacts_4.txt","instructions_4.txt","results.txt","reports.txt"};
		PhoneProcessor testPhoneProcessor = new PhoneProcessor(params);	
		
		testPhoneProcessor.loadInput();
		assertEquals(testPhoneProcessor.getPhoneBook().phoneBook.size(),3);
	}
	
	@Test
	public void readInstructionContacts4_AddDelete() {
		String[] params = {"contacts_4.txt","instructions_4.txt","results.txt","reports.txt"};
		PhoneProcessor testPhoneProcessor = new PhoneProcessor(params);	
		
		testPhoneProcessor.readInstruction();
		assertEquals(testPhoneProcessor.getPhoneBook().phoneBook.size(),8);
	}
	
	@Test
	public void readInstructionContacts5_Query() {
		String[] params = {"contacts_5.txt","instructions_5.txt","results.txt","reports.txt"};
		PhoneProcessor testPhoneProcessor = new PhoneProcessor(params);	
		
		testPhoneProcessor.readInstruction();
		assertEquals(testPhoneProcessor.getPhoneBook().getQueryPhoneBookList().size(),3);
	}
}
