package be.cheops.kontich.boatload.consumer;

import lombok.Getter;

public class Enterprise {

	@Getter
	private int topicInt;

	Enterprise(int topicInt) {

		this.topicInt = topicInt;
	}
}
