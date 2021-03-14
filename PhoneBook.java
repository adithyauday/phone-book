package comp9103;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {
    ArrayList<PhoneContact> phoneBook;
    LinkedHashMap<PhoneBook,String> queryPhoneBookList;
    PhoneBook queryPhoneBook; 

    public PhoneBook(){
        phoneBook = new ArrayList<PhoneContact>(); 
        queryPhoneBookList = new LinkedHashMap<PhoneBook,String>(); 
    }
    //Add valid PhoneContact to the phoneBook or if exists update contact and remove the contact if
    //name or birthday is not valid.
    public void addPhoneContact(PhoneContact pc) {
    	boolean isContactExists = false;
        if(pc.isValidToAdd()) {
        	for(PhoneContact contact: phoneBook) {
            	if(contact.getName().equals(pc.getName()) && contact.getBirthday().equals(pc.getBirthday()) ) {
            		isContactExists = true;
            		if(pc.getPhoneNumber()!=null)
            			contact.setPhoneNumber(pc.getPhoneNumber());
            		if(pc.getEmail()!=null)
            			contact.setEmail(pc.getEmail());
            		if(pc.getAddress()!=null)
            			contact.setaddress(pc.getAddress());
            	}
            }
        	if(!isContactExists)
                phoneBook.add(pc);
        }
        else {
        	List<PhoneContact> found = new ArrayList<PhoneContact>();
        	for(PhoneContact contact: phoneBook) {
        		if(contact.getName().equals(pc.getName())) {
        			found.add(contact);
        		}
        	}
			phoneBook.removeAll(found);
        }
    }
    //Add PhoneContact using input String
    public void addPhoneContact(String param) {
        PhoneContact pc = new PhoneContact(param);        
        this.addPhoneContact(pc);        	
    }
    //Delete contact from list
    public void removePhoneContact(String param) {
        String[] queryTerms = param.split(";");
        int i= 0;
        while(i< phoneBook.size()) {
            if(phoneBook.get(i).getName().equalsIgnoreCase(queryTerms[0].trim())) {
            	if(queryTerms.length == 1)
            		phoneBook.remove(i);
            	else if(phoneBook.get(i).getBirthday().equalsIgnoreCase(validBirthday(queryTerms[1].trim()))) 
            		phoneBook.remove(i);
            	else
            		++i;
            } else {
                ++i;
            }
            
        }
    }
    //Check if queried birthday is a valid date and formated
    private String validBirthday(String bday) {
			String[] temp;
			if(bday.matches("\\d+\\D\\d+\\D\\d+")) {
				temp= bday.split("\\D");
				if(temp.length== 3) {
					for(int i= 0; i< 2; ++i) {
						if(temp[i].length() < 2)
							temp[i] = "0"+ temp[i];
					}
					bday= temp[0] + "-"+ temp[1] + "-"+ temp[2];
				}
			}
			return bday;
		}
	
    //Add contacts queried to a list
    public void queryPhoneContacts(String instruction) {
    	queryPhoneBook = new PhoneBook(); 
    	Scanner sc= new Scanner(instruction);
        String keyword, param;
        if(sc.hasNext()) {
            keyword= sc.next();
            if(sc.hasNextLine()) {
                param= sc.nextLine().trim();
                if(keyword.equalsIgnoreCase("name")) {
                    for(PhoneContact pc: phoneBook) {
                    	if(param.equals(pc.getName()))
                    		queryPhoneBook.addPhoneContact(pc);
                    }
                } else if(keyword.equalsIgnoreCase("birthday")) {
                	for(PhoneContact pc: phoneBook) {
                    	if(param.equals(pc.getBirthday()))
                    		queryPhoneBook.addPhoneContact(pc);
                    }
                } else if(keyword.equalsIgnoreCase("phone")) {
                	for(PhoneContact pc: phoneBook) {
                    	if(param.equals(pc.getPhoneNumber()))
                    		queryPhoneBook.addPhoneContact(pc);
                    }
                } else if(keyword.equalsIgnoreCase("email")) {
                	for(PhoneContact pc: phoneBook) {
                    	if(param.equals(pc.getEmail()))
                    		queryPhoneBook.addPhoneContact(pc);
                    }
                } else if(keyword.equalsIgnoreCase("address")) {
                	for(PhoneContact pc: phoneBook) {
                    	if(param.equals(pc.getAddress()))
                    		queryPhoneBook.addPhoneContact(pc);
                    }
                }
            }
            queryPhoneBookList.put(queryPhoneBook,instruction);
        }
        sc.close();
    }
    // Formatting how query file should be displayed
    public String queryToString() {
    	StringBuilder sb= new StringBuilder();    	
    	sortPhoneBook();
        for(PhoneBook p: queryPhoneBookList.keySet()){
        	sb.append("====== query" + queryPhoneBookList.get(p) + " ======\n");
            sb.append(p.toString());
            sb.append("\n\n====== end of query" + queryPhoneBookList.get(p) + " ======\n");
            sb.append("\n");
        }
        return sb.toString().trim();
    }
    //Sort the phone contacts by name and birthday to display queried results
    private void sortPhoneBook() {
    	for(PhoneBook p: queryPhoneBookList.keySet()){
    		SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
       		p.getPhoneBook().sort((o1,o2) -> {
				try {
					return dateFormat.parse(o1.getBirthday()).compareTo(dateFormat.parse(o2.getBirthday()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
			}); 
       		p.getPhoneBook().sort((o1,o2) -> o1.getName().compareTo(o2.getName()));
    		
    	}
    }
    //Format how result file will display phone contacts
    public String toString() {
        StringBuilder sb= new StringBuilder();
        for(PhoneContact p: phoneBook){
            sb.append(p.toString());
            sb.append("\n");
        }
        return sb.toString().trim();
    }
    //Get the phoneBook
    public ArrayList<PhoneContact> getPhoneBook() {
        return phoneBook;
    }
    //Set the phoneBook
    public void setPhoneBook(ArrayList<PhoneContact> aphoneBook) {
        this.phoneBook = aphoneBook;
    }
    //Get the phoneBook
    public LinkedHashMap<PhoneBook,String> getQueryPhoneBookList() {
        return queryPhoneBookList;
    }
}
