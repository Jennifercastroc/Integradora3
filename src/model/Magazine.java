package model;
import java.util.UUID;
import java.util.Random;


import java.util.Calendar;

public class Magazine extends BibliographicProduct{
	
	private int periodicBroadcast; //Time counts on days
	private int numbActiveSusbcriptions;
	
	
	public Magazine(String name, int numbPages, Calendar publicationDate, String url, double prize,
			int periodicBroadcast) {
		super(name, numbPages, publicationDate, url, prize);
		this.periodicBroadcast = periodicBroadcast;
		this.numbActiveSusbcriptions = 0;
		this.id=generateRandomId(3);
	}


	
	private String generateRandomId(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        
        return sb.toString();
    }
	

	@Override 
	public String toString() {
		return "Magazine [periodicBroadcast=" + periodicBroadcast + ", numbActiveSusbcriptions="
				+ numbActiveSusbcriptions + " ID= " + id +"]";
	}
	
	

}
