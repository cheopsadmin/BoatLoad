package be.cheops.kontich.boatload.domain;

public class Beacon {

	private long id;

	private int counter = 0;

	Beacon(long id) {

		this.id = id;
	}

	public void pass(int boatId) {

		// System.out.println("Ping: (from) " + id + " reading: " + boatId);

		counter++;
	}

	public int collectAndForget() {

		int temp = counter;

		counter = 0;

		return temp;
	}

}
