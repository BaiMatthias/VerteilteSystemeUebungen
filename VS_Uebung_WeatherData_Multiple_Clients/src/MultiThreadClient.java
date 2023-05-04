import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Diese Klasse repraesentiert einen Client, der sich mit einem Server verbinden
 * und nach Wetterdaten fragen kann
 * Jeder Client läuft dabei in einem eigenen Thread
 * @author Matze
 *
 */
public class MultiThreadClient implements Runnable {

	private Socket socket;
	private PrintWriter out;
	private BufferedReader br;

	public MultiThreadClient() {
		IConnector connector = new LocalConnector();
		try {
			socket = new Socket(connector.getServer(), connector.getPort());
			out = new PrintWriter(socket.getOutputStream());
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Socket getSocket() {
		return socket;
	}

	public PrintWriter getOut() {
		return out;
	}

	public BufferedReader getBr() {
		return br;
	}
/**
 * Gibt alle Ressourcen frei
 */
	public void closeAll(){
		try {
			this.socket.close();
			this.out.close();
			this.br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	/**
	 * Thread-Methode
	 * Uebergeben des Datums an den Server und Auswerten der Antwort
	 */
	@Override
	public void run() {
		String result = "";
		Scanner s= new Scanner(System.in);
		String date = s.nextLine(); // Datum eingeben
		out.println(date);
		out.flush();
		

		try {
			while ((result = br.readLine()) != null) { // auf Antwort warten

				if (result.equalsIgnoreCase("-1")) { // Falsches Datum
					System.out.println("Keine Temperaturdaten für diese Eingabe vorhanden.");
				} else {
					int max = Integer.MIN_VALUE;
					int min = Integer.MAX_VALUE;
					int avg = 0;
					System.out.println("Temperaturdaten:");
					// Trennen der Temperaturen
					String[] resultarr = result.split(",");
					for (int i = 1; i < resultarr.length; i++) { // auf Index 0
																	// liegt das
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
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
