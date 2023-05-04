import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Diese Klasse repraesentiert einen Thread, in dem die Client-Server-Interaktion abläuft
 * @author MatzesPC
 *
 */

public class ServerServiceThread extends Thread {

	private BufferedReader in;
	private PrintWriter out;
	private Socket clientSocket;
	private final String PATH = "C:\\Users\\MatzesPC\\Desktop\\Weatherdata.csv";

	/**
	 * Jeder Thread erhält ein eigenen Output und InputStream, damit Anfragen gesondert bearbeitet werden
	 * @param clientSocket Der akzeptierte Socket des Clients
	 */
	public ServerServiceThread(Socket clientSocket) {
		try {
			
			this.clientSocket = clientSocket;
			in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			out = new PrintWriter(this.clientSocket.getOutputStream());
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
/**
 * Die Thread-Methode, es wird auf eine Anfrage des verbundenen Clients gewartet
 * Sobald Anfrage kommt, wird das Ergebnis geliefert
 */
	public void run() {
		String date; 
		while(true){
			
			try {
				if((date=in.readLine()) != null){
				
					WeatherDataHandler weather = new WeatherDataHandler(PATH);
					Thread.sleep(4000);
					out.println(weather.getWeatherDataFromFile(date)); // Sende die Wetterdaten aus der CSV-Datei
					out.flush();
					break;

				}
				clientSocket.close();
				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			
		}
		
		
	}

}
