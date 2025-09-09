package be.cheops.kontich.boatload.kafka;

import java.util.ArrayList;

import org.apache.kafka.clients.admin.NewTopic;

import be.cheops.kontich.boatload.domain.Beacon;

public class Country {

	private ArrayList<Beacon> beaconz = new ArrayList<Beacon>();

	private final NewTopic myTopic;

	private ProducerMain producerMain = new ProducerMain();

	private ArrayList<String> queuedBeacons = new ArrayList<String>();
	private ArrayList<String> queuedBoats = new ArrayList<String>();

	public Country(int sequenceNumber) {

		myTopic = Topic.createTopic(sequenceNumber);

		System.out.println("topic: " + sequenceNumber + " fetched...");
	}

	public void addBeacon(Beacon beacon) {

		beaconz.add(beacon);
	}

	public synchronized void send(String beacon, String boat) {

		queuedBeacons.add(beacon);
		queuedBoats.add(boat);

		if (queuedBeacons.size() > 10) {

			producerMain.send(myTopic.name(), queuedBeacons, queuedBoats);

			queuedBeacons = new ArrayList<String>();
			queuedBoats = new ArrayList<String>();
		}
	}

}
