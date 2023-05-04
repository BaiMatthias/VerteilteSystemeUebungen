import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
/**
 * Diese Klasse kuemmert sich um das Auslesen der CSV-Datei
 * @author Matze
 *
 */

public class WeatherDataHandler {
	private String path;
	
	public WeatherDataHandler(String path){
		this.path = path;
	}
	
	/**
	 * Sucht nach Wetterdaten am uebergegebenen Datum
	 * @param date das Datum, nach dem gesucht werden soll
	 * @return Ein String, der die Wetterdaten enthaelt, -1, wenn keine Daten gefunden wurden.
	 */
	public String getWeatherDataFromFile(String date){
		File file = new File(path);
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null){
				if(line.isEmpty()){
					br.close();
					return "-1";
				}
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

