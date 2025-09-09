package be.cheops.kontich.boatload.kafka;

import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerMain {

	final Producer<String, String> producer;

	static ProducerMain instance = new ProducerMain();

	public ProducerMain() {

		Properties props = new Properties();

		props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

		try (final Producer<String, String> producer = new KafkaProducer<>(props)) {

			this.producer = producer;
		}

	}

	static void send(String topic, String user, String item) {

		instance.producer.send(new ProducerRecord<>(topic, user, item), (event, ex) -> {
			if (ex != null)
				ex.printStackTrace();
			else
				System.out.printf("Produced event to topic %s: key = %-10s value = %s%n", topic, user, item);
		});
	}
}
