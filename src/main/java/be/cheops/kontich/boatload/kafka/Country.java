package be.cheops.kontich.boatload.kafka;

import java.util.ArrayList;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import be.cheops.kontich.boatload.domain.Beacon;

public class Country {

	private ArrayList<Beacon> beaconz = new ArrayList<Beacon>();

	private final NewTopic myTopic;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public Country(int sequenceNumber) {

		myTopic = Topic.createTopic(sequenceNumber);

		System.out.println("topic: " + sequenceNumber + " fetched...");
	}

	public void addBeacon(Beacon beacon) {

		beaconz.add(beacon);
	}

	public void send(String beacon, String boat) {
		ProducerMain.send(myTopic.name(), beacon, boat);
	}

}
