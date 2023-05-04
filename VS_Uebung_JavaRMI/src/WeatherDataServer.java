import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
/**
 * Diese Klasse stellt den Server dar, deren Methoden remote via RMI aufgerufen werden
 * @author MatzesPC
 *
 */
public class WeatherDataServer extends UnicastRemoteObject implements WeatherInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String path;
	private final String PATH = "C:\\Users\\MatzesPC\\Desktop\\Weatherdata.csv";
	
	
	
	public WeatherDataServer() throws RemoteException {
		this.path = PATH;
	}
	
	public String getPath(){
		return path;
	}

	/**
	 * Startet den Dienst des Servers
	 */
	public void startServerService(){
		
        try {
        	LocateRegistry.createRegistry(8181); // Speicher den Dienst in der Registry
			Naming.rebind("//localhost:8181/Server", this); // Benenne den Dienst
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}            
        System.err.println("Server ist bereit.");
	}
	/**
	 * Sucht nach Wetterdaten passend zum uebergebenen Datum und gibt diese zurueck
	 * @return  Wetterdaten
	 */
	@Override
	public String getWeatherData(String date) throws RemoteException {
		
		File file = new File(path);
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null){
				if(line.toLowerCase().contains(date.toLowerCase())){ // Vergleich mit uebergegebenen Datum und Datum aus der CSV-Datei
				   br.close();
				   return line;	
				}
			}
			br.close();
			return "-1";
		}
		catch(Exception e){
			e.printStackTrace();
			
			return "Es ist ein Fehler aufgetreten";
		}
	}

	
	
}
