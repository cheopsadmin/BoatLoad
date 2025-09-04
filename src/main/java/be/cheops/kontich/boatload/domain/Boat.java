package be.cheops.kontich.boatload.domain;

import lombok.Getter;

public class Boat {

	private Ocean ocean;

	private int id;

	@Getter
	private boolean arrived = false;

	public Boat(Ocean ocean, int i) {

		this.ocean = ocean;

		id = i;
	}

	public void vaar() {

		new Thread(new Waiter()).start();
	}

	private class Waiter implements Runnable {

		@Override
		public void run() {

			int napTime = (int) (Math.random() * 20_000) + 10_000;

			for (int i = 0; i < 10_000; i++) {

				try {
					Thread.sleep(napTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				ocean.getABeacon().pass(id);

				napTime = (int) (Math.random() * 20_000) + 10_000;
			}

			arrived = true;
		}
	}

}
