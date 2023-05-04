import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Diese Klasse repraesentiert einen Server, mit dem sich ein Client verbinden kann und Datumsangaben entgegennimmt und Wetterdaten weiterleitet
 * @author Matze
 *
 */
public class Server {

	private final String PATH = "C:\\Users\\MatzesPC\\Desktop\\Weatherdata.csv";
	private ServerSocket server;
	
	
	
	public Server(){
		IConnector connector = new LocalConnector();
		try {
			this.server = new ServerSocket(connector.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Verbindet sich mit dem Client und wartet auf eine Nachricht.
	 * Sobald eine Nachricht angekommen ist, wird diese an den WeatherDataHandler weitergeleitet
	 * Ergebnis der Anfrage wird zurueck an den Client gesendet
	 */
	public void openConnection(){
		
		try {
			    
			
				System.out.println("Verbindung wird aufgebaut...");
				Socket clientSocket = this.server.accept(); // Verbindung mit Client
				System.out.println("Verbindung wurde aufgebaut!");
				
				// Nachricht des Clients wird hier empfangen
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				//Nachricht an Client wird hier gesendet
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
				String date; 
				
				while(true){
					if((date=in.readLine()) != null){
						WeatherDataHandler weather = new WeatherDataHandler(this.PATH); // Objekt fuer Extraktion der Wetterdaten
						out.println(weather.getWeatherDataFromFile(date)); // Sende die Wetterdaten aus der CSV-Datei
						out.flush();
						break;
					}
					
				}
				System.out.println("Server wird geschlossen.");
				this.server.close();
				in.close();
				out.close();
				clientSocket.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
