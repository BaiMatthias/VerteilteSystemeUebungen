import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Diese Klasse repraesentiert einen Client, der sich mit einem Server verbinden
 * und nach Wetterdaten fragen kann
 * 
 * @author Matze
 *
 */
public class Client {

	private Socket socket; // Wird fuer den Verbindungsaufbau zum Server benoetigt
	private PrintWriter out;
	private BufferedReader br;

	public Client() {
		IConnector connector = new LocalConnector();
		try {
			socket = new Socket(connector.getServer(), connector.getPort());
			out = new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (UnknownHostException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void printWeatherData(String date) {
		try {

			out.println(date);
			out.flush();

			String result;

			while (true) {
				if ((result = br.readLine()) != null) { // auf Antwort warten

					if (result.equalsIgnoreCase("-1")) { // Falsches Datum
						System.out.println("Keine Temperaturdaten für diese Eingabe vorhanden.");
					} else {
						int max = Integer.MIN_VALUE;
						int min = Integer.MAX_VALUE;
						int avg = 0;
						System.out.println("Temperaturdaten:");
						// Trennen der Temperaturen
						String[] resultarr = result.split(",");
						for (int i = 1; i < resultarr.length; i++) { // auf
																		// Index
																		// 0
																		// liegt
																		// das
																		// Datum
							System.out.println(i - 1 + " Uhr: " + resultarr[i] + " Grad");
							if (max < Integer.parseInt(resultarr[i])) {
								max = Integer.parseInt(resultarr[i]); // Extraktion
																		// der
																		// höchsten
																		// Temperatur
							}
							if (min > Integer.parseInt(resultarr[i])) {
								min = Integer.parseInt(resultarr[i]); // Extraktion
																		// der
																		// geringsten
																		// Temperatur
							}
							avg += Integer.parseInt(resultarr[i]);
						}
						avg = avg / 24; // Durchschnittstemperatur
						System.out.println("Höchsttemperatur: " + max + " Grad");
						System.out.println("Minimaltemperatur " + min + " Grad");
						System.out.println("Durchschnittstemperatur: " + avg + " Grad");
						break;
					}

				}
			}

			out.print(date);
			out.close();
			socket.close();

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		} finally {
			if (socket != null) {
				try {
					socket.close();
					System.out.println("Socket geschlossen...");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}


}
