package be.cheops.kontich.boatload.log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Log {

	private ArrayList<String> queue = new ArrayList<String>();

	public synchronized void writeToLog(String message) {

		queue.add(message);

		if (queue.size() > 10) {

			try {

				flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void flush() throws IOException {

		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\log\\log.log", true));

		for (String message : queue) {

			writer.append(message);
			writer.append("\n");
		}

		writer.close();
		
		queue = new ArrayList<String>();
	}

	public static void main(String[] args) {

		Log log = new Log();

		for (int i = 0; i < 1000; i++) {

			log.writeToLog("message: " + i);
		}
	}

}
