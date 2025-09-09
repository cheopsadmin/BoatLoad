package be.cheops.kontich.boatload.consumer;

import static be.cheops.kontich.boatload.domain.Ocean.BEACONZ_FOR_EACH_COUNTRY;
import static be.cheops.kontich.boatload.domain.Ocean.NB_OF_BEACONZ;

import java.util.ArrayList;
import java.util.Collections;

public class ConsumerMain {

	private static int numberOfTopics = NB_OF_BEACONZ / BEACONZ_FOR_EACH_COUNTRY;

	private ArrayList<Enterprise> myEnterprises = new ArrayList<Enterprise>();

	public static void main(String[] args) {

		ArrayList<Integer> usedInts = new ArrayList<Integer>();

		ConsumerMain consumer = new ConsumerMain();

		for (int i = 0; i < numberOfTopics / 10; i++) {

			int index = (int) (Math.random() * numberOfTopics);

			while (usedInts.contains(index)) {

				index = (int) (Math.random() * numberOfTopics);
			}

			consumer.addEnterprise(new Enterprise(index));

			usedInts.add(index);
		}

		// consumer.output();

		consumer.listen();
	}

	private void addEnterprise(Enterprise enterprise) {

		myEnterprises.add(enterprise);
	}

	private void output() {

		ArrayList<Integer> usedInts = new ArrayList<Integer>();

		for (Enterprise enterprise : myEnterprises) {

			usedInts.add(enterprise.getTopicInt());
		}

		Collections.sort(usedInts);

		for (int i : usedInts) {

			System.out.println(i);
		}
	}

	private void listen() {

		for (Enterprise enterprise : myEnterprises) {

			enterprise.listen();
		}
	}

}
