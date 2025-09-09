package be.cheops.kontich.boatload.consumer;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import be.cheops.kontich.boatload.kafka.Topic;
import be.cheops.kontich.boatload.log.Log;
import lombok.Getter;

public class Enterprise {

	@Getter
	private int topicInt;

	private final NewTopic myTopic;

	Enterprise(int topicInt) {

		this.topicInt = topicInt;

		myTopic = Topic.createTopic(topicInt);
	}

	void listen() {

		(new Thread(new Listener())).start();
	}

	private class Listener implements Runnable {

		private Log log = new Log();

		@Override
		public void run() {

			Properties props = new Properties() {
				{

					// Fixed properties
					put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
					put(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
					put(GROUP_ID_CONFIG, "kafka-java-getting-started");
				}
			};

			props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

			try (final Consumer<String, String> consumer = new KafkaConsumer<>(props)) {
				consumer.subscribe(Arrays.asList(myTopic.name()));
				while (true) {
					ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
					for (ConsumerRecord<String, String> record : records) {
						String key = record.key();
						String value = record.value();
						log.writeToLog(String.format("Consumed event from topic %s: key = %-10s value = %s",
								myTopic.name(), key, value));
					}
				}
			}
		}
	}
}
