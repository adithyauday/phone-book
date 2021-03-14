package comp9103;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneContact {
    private String name;
    private String birthday;
    private String phoneNumber;
    private String email;
    private String address;
    
    public PhoneContact(){
        name = null;
        birthday = null;
        phoneNumber = null;
        email = null;
        address = null;
    }
    //Constructor for PhoneContact
    public PhoneContact(String params){
        Scanner scan= new Scanner(params);
        scan.useDelimiter(";");
            while(scan.hasNext()) {
                String instruction = scan.next();
                Scanner sc= new Scanner(instruction);
                String keyword, param;
                if(sc.hasNext()) {
                    keyword= sc.next();
                    if(sc.hasNextLine()) {
                        param = sc.nextLine().trim();
                        if(keyword.trim().equalsIgnoreCase("name")) {
                            setName(param);
                        } else if(keyword.trim().equalsIgnoreCase("birthday")) {
                            setBirthday(param);
                        }else if(keyword.trim().equalsIgnoreCase("phone")) {
                            setPhoneNumber(param);
                        } else if(keyword.trim().equalsIgnoreCase("address")) {
                            setaddress(param);
                        } else if(keyword.trim().equalsIgnoreCase("email")) {
                            setEmail(param);
                        }
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
    }
    //Getter and setter for the Contact details
    public String getName(){
        return name;
    };
    public String getBirthday(){
        return birthday;
    };
    public String getPhoneNumber(){
        return phoneNumber;
    };
    public String getEmail(){
        return email;
    };
    public String getAddress(){
        return address;
    };
    public void setName(String aname){
        name = aname;
    };
    public void setBirthday(String abirthday){
        birthday = abirthday;
    };
    
    public void setPhoneNumber(String aphoneNumber){
        phoneNumber = aphoneNumber;
    };
    public void setEmail(String aemail){
        email = aemail;
    };
    public void setaddress(String aaddress){
        address = aaddress;
    };

    public void concatAddress(String aaddress){
        address = address + " " + aaddress;
    }
    // Format of how PhoneConatcts should be displayed in output
    public String toString() {
    	String contactString;
    	contactString = String.format("name: %s\nbirthday: %s\n", name,birthday);
    	if(address != null)
    		contactString = contactString + String.format("address: %s\n", address);
    	if(phoneNumber != null)
    		contactString = contactString + String.format("phone: %s\n", phoneNumber);
    	if(email != null)
    		contactString = contactString + String.format("email: %s\n", email);    	
    	return contactString;
    }
    // Check if contact is valid to add
    public boolean isValidToAdd()
    {
    	if(isValidName() && isValidBirthday()) {
    		isValidAddress();
    		validatePhoneNumber();
    		validateEmail();
    		return true;
    	}
    	else
    		return false;
    }
    //Check is email is valid to add
	private void validateEmail() {
		if(email!=null) {
			//String regex = "^(.+)@(.+)$";
			String regex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                    "[a-zA-Z0-9_+&*-]+)*@" + 
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                    "A-Z]{2,7}$"; 
                      
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);		 
			if(!matcher.matches())
				email = null;
		}
	}
	//Check if phoneNumber is valid
	private void validatePhoneNumber() {
		if(phoneNumber!=null) {
			try {
				long number = Long.parseLong(phoneNumber);
				phoneNumber = Long.toString(number);
			} catch ( NumberFormatException e) {
				phoneNumber = null;
			}
		}
	}
	//Check if Address is valid
	private void isValidAddress() {
		if(address!=null) {
			String regex = "(([^/ ]+)/)?([^ ]+) ([^,]+), ([^,]+), ([^ ]+) (\\d+)";
		    Pattern pattern = Pattern.compile(regex);
		    Matcher matcher = pattern.matcher(address);		 
			if(!matcher.matches())
				address = null;
		}
	}
	//Check if birthday is valid
	private boolean isValidBirthday() {
		if(birthday!=null) {
			SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy");
			String dateStr;
			dateStr= getBirthday();
			String[] temp;
			if(dateStr.matches("\\d+\\D\\d+\\D\\d+")) {
				temp= dateStr.split("\\D");
				if(temp.length== 3) {
					for(int i= 0; i< 2; ++i) {
						if(temp[i].length() < 2)
							temp[i] = "0"+ temp[i];
					}
					dateStr= temp[0] + "-"+ temp[1] + "-"+ temp[2];
				}
			}
			try{
				dateFormat.parse(dateStr);
				birthday = dateStr;
				return true;
			} catch(ParseException e) {
				birthday= null;
			}
		}
		return false;
	}
	// Check if name is valid
	private boolean isValidName() {
		if(name!= null && name.matches("[a-zA-Z\\s]*")) {
			return true;
		}
		else{
			return false;
		}
	}
    
}
