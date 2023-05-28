package model;

import java.util.Calendar;
import java.util.UUID;
import java.util.Random;

public class Magazine extends BibliographicProduct {
	
	private int periodicBroadcast; // Time counts on days
	private int numbActiveSubscriptions;
	private MagazineType magazineType;

	public Magazine(String name, int numbPages, Calendar publicationDate, String url, double prize,
			int periodicBroadcast, MagazineType magazineType) {
		super(name, numbPages, publicationDate, url, prize);
		this.periodicBroadcast = periodicBroadcast;
		this.numbActiveSubscriptions = 0;
		this.id = generateRandomId(3);
		this.magazineType = magazineType;
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

	public int getPeriodicBroadcast() {
		return periodicBroadcast;
	}

	public void setPeriodicBroadcast(int periodicBroadcast) {
		this.periodicBroadcast = periodicBroadcast;
	}

	public int getNumbActiveSubscriptions() {
		return numbActiveSubscriptions;
	}

	public void setNumbActiveSubscriptions(int numbActiveSubscriptions) {
		this.numbActiveSubscriptions = numbActiveSubscriptions;
	}

	public MagazineType getMagazineType() {
		return magazineType;
	}

	public void setMagazineType(MagazineType magazineType) {
		this.magazineType = magazineType;
	}

	@Override
	public String toString() {
		return "Magazine [periodicBroadcast=" + periodicBroadcast + ", numbActiveSubscriptions="
				+ numbActiveSubscriptions + ", ID=" + id + ", magazineType=" + magazineType + "]";
	}
}

