package be.cheops.kontich.boatload.kafka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;

public class Topic {
	
	public static void main(String[] args) {

		createTopic(0);
	}

	public static NewTopic createTopic(int countryInt) {

		NewTopic newTopic = null;

		final String countryTopic = "Country_Topic_vier_" + countryInt;

		Properties props = new Properties();

		props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

		try (final AdminClient adminClient = AdminClient.create(props)) {
			try {
				// Define topic
				newTopic = new NewTopic(countryTopic, 4, (short) 1);

				// Create topic, which is async call.
				final CreateTopicsResult createTopicsResult = adminClient.createTopics(Collections.singleton(newTopic));

				// Since the call is Async, Lets wait for it to complete.
				createTopicsResult.values().get(countryTopic).get();

			} catch (InterruptedException | ExecutionException e) {
				if (!(e.getCause() instanceof TopicExistsException))
					throw new RuntimeException(e.getMessage(), e);
			}
		}

		return newTopic;
	}
}
