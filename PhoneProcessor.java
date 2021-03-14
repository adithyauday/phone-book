package comp9103;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class PhoneProcessor {
	private File inputFile;
    private File instructionFile;
    private File outputFile;
    private File reportFile;
    private PhoneBook phoneBook;
    public PhoneProcessor(String[] s) {
        inputFile= new File(s[0]);
        instructionFile= new File(s[1]);
        outputFile= new File(s[2]);
        reportFile= new File(s[3]);
        phoneBook= new PhoneBook();
    }
    //Save the PhoneBook into the output files
    private void saveResult() {
        try{
            PrintWriter out= new PrintWriter(new FileOutputStream(outputFile));
            out.println(phoneBook.toString());
            out.close();
            out= new PrintWriter(new FileOutputStream(reportFile));
            out.println(phoneBook.queryToString());
            out.close();
        } catch(FileNotFoundException e) {
                e.printStackTrace();
        }
    }
    // load contacts from contact files into phonebook
    public void loadInput() {
        try{
            PhoneContact phoneContact = new PhoneContact();
            Scanner scan= new Scanner(inputFile);
            boolean isPartOfAddress = false;
            while(scan.hasNextLine()) {
                String instruction = scan.nextLine();
                Scanner sc= new Scanner(instruction);
                if(instruction.trim().isEmpty()) {
                    phoneBook.addPhoneContact(phoneContact);
                    phoneContact = new PhoneContact();
                }

                String keyword, param;
                if(sc.hasNext()) {
                    keyword= sc.next();
                    if(sc.hasNextLine()) {
                        param = sc.nextLine().trim();
                        if(keyword.equalsIgnoreCase("name")) {
                            phoneContact.setName(param);
                            isPartOfAddress = false;
                        } else if(keyword.equalsIgnoreCase("birthday")) {
                            phoneContact.setBirthday(param);
                            isPartOfAddress = false;
                        }else if(keyword.equalsIgnoreCase("phone")) {
                            phoneContact.setPhoneNumber(param);
                            isPartOfAddress = false;
                        } else if(keyword.equalsIgnoreCase("address")) {
                            phoneContact.setaddress(param);
                            isPartOfAddress = true;
                        } else if(keyword.equalsIgnoreCase("email")) {
                            phoneContact.setEmail(param);
                            isPartOfAddress = false;
                        } else if(isPartOfAddress){
                            phoneContact.concatAddress(instruction.trim());
                        }
                    }
                    else{
                    	if(isPartOfAddress){
                            phoneContact.concatAddress(instruction.trim());
                        }
                        continue;
                    }
                    
                    sc.close();
                }else{
                    continue;
                }
            }
            scan.close();
            phoneBook.addPhoneContact(phoneContact);
            } catch(FileNotFoundException e) {
                e.printStackTrace();
        }
    }
    //Read the instruction file and perform the instructions
    public void readInstruction() {
        try{
            Scanner scan= new Scanner(instructionFile);
            while(scan.hasNextLine()) {
                String instruction = scan.nextLine();
                Scanner sc= new Scanner(instruction);
                String keyword, param;
                if(sc.hasNext()) {
                    keyword= sc.next();
                    if(sc.hasNextLine()) {
                        param= sc.nextLine();
                        if(keyword.equalsIgnoreCase("add")) {
                            phoneBook.addPhoneContact(param);
                        } else if(keyword.equalsIgnoreCase("delete")) {
                            phoneBook.removePhoneContact(param);
                        } else if(keyword.equalsIgnoreCase("query")) {
                        	phoneBook.queryPhoneContacts(param);
                        } 
                    } else if(keyword.equalsIgnoreCase("save")) {
                        this.saveResult();
                    }
                    else{
                        continue;
                    }
                    sc.close();
                }else{
                    continue;
                }
            }
            scan.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public PhoneBook getPhoneBook() {
    	return phoneBook;
    }
}

