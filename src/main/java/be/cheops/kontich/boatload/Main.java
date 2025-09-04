package be.cheops.kontich.boatload;

import be.cheops.kontich.boatload.domain.Ocean;

public class Main {

	public static void main(String[] args) {

		execute();
	}

	public static void execute() {

		Ocean pacific = new Ocean();

		pacific.vertrek();

		int counter = 0;

		long total = 0;

		do {

			try {
				Thread.sleep(1_000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			int temp = pacific.collect();

			System.out.println("the: " + counter++ + "th second we've gotten: " + temp + " requests...");

			total += temp;

		} while (!pacific.allArived());

		System.out.println(
				"All is well that ends well :) :) :) (this took us: " + counter + " precious seconds of our lives...");
		System.out.println("(But " + total + " events is worth the wait :))");
	}

}
