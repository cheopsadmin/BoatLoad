package be.cheops.kontich.boatload.domain;

import be.cheops.kontich.boatload.kafka.Country;

public class Ocean {

	private static final int NB_OF_BOATZ = 100_000;
	private static final int NB_OF_BEACONZ = 20_000;
	private static final int BEACONZ_FOR_EACH_COUNTRY = 100;

	private Beacon[] beaconz = new Beacon[NB_OF_BEACONZ];
	private Boat[] boatz = new Boat[NB_OF_BOATZ];
	private Country[] countries = new Country[NB_OF_BEACONZ / BEACONZ_FOR_EACH_COUNTRY];

	public Ocean() {

		for (int i = 0; i < NB_OF_BEACONZ / BEACONZ_FOR_EACH_COUNTRY; i++) {

			countries[i] = new Country(i);
		}

		for (int i = 0; i < NB_OF_BEACONZ; i++) {

			beaconz[i] = new Beacon(i, countries[i / BEACONZ_FOR_EACH_COUNTRY]);

			countries[i / BEACONZ_FOR_EACH_COUNTRY].addBeacon(beaconz[i]);
		}

		for (int i = 0; i < NB_OF_BOATZ; i++) {

			boatz[i] = new Boat(this, i);

			if (i % 100 == 0) {
				System.out.print(".");
			}
		}
	}

	public void vertrek() {

		System.out.println("Vertrekking Boats... (no pun here :()");

		int counter = 0;

		for (Boat boat : boatz) {

			if (counter % 100 == 0) {
				System.out.print(".");
			}
			if (counter % 1000 == 0) {
				System.out.println();
			}

			counter++;

			boat.vaar();
		}

		System.out.println("Boats Inited... ((still) no pun...)");
		System.out.println("Enwezijnvertrokke!!!");
	}

	Beacon getABeacon() {

		return beaconz[(int) (Math.random() * NB_OF_BEACONZ)];
	}

	public int collect() {

		int result = 0;

		for (Beacon beacon : beaconz) {

			result += beacon.collectAndForget();
		}

		return result;
	}

	public boolean allArived() {

		boolean result = false;

		int counter = 0;

		for (Boat boat : boatz) {

			boolean isArrived = boat.isArrived();

			if (isArrived) {

				counter++;
			}

			result &= isArrived;
		}

		System.out.print(" --- " + counter + " boats have arrived.");

		return result;
	}
}
