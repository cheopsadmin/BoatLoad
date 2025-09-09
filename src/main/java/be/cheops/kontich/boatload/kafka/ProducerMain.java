package be.cheops.kontich.boatload.kafka;

import static org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG;
import static org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class ProducerMain {

	private final Properties props;

	public ProducerMain() {

		props = new Properties() {
			{

				// Fixed properties
				put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getCanonicalName());
				put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getCanonicalName());
			}
		};

		props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	}

	public void send(String topic, ArrayList<String> beacons, ArrayList<String> boats) {

		try (final Producer<String, String> producer = new KafkaProducer<>(props)) {

			for (int i = 0; i < beacons.size(); i++) {

				final String beacon = beacons.get(i);
				final String boat = boats.get(i);

				producer.send(new ProducerRecord<>(topic, beacon, boat), (event, ex) -> {
					if (ex != null)
						ex.printStackTrace();
					else
						System.out.printf("Produced event to topic %s: key = %-10s value = %s%n", topic, beacon, boat);
				});
			}
		}
	}
}
