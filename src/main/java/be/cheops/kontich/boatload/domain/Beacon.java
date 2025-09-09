package be.cheops.kontich.boatload.domain;

import be.cheops.kontich.boatload.kafka.Country;

public class Beacon {

	private long id;

	private int counter = 0;
	
	private Country country;

	Beacon(long id, Country country) {

		this.id = id;
		this.country = country;
	}

	public void pass(int boatId) {

		country.send("Beacon: " + id, "Boat: " + boatId);

		counter++;
	}

	public int collectAndForget() {

		int temp = counter;

		counter = 0;

		return temp;
	}

}
