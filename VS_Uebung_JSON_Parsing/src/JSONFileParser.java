import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.*;

/**
 * Diese Klasse repraesentiert einen JSON-Parser, der JSON-Dateien ueber Flugbuchungen auslesen kann
 * @author MatzesPC
 *
 */


public class JSONFileParser {
	
	private String path;
	
	public JSONFileParser(String path){
		this.path = path;
	}

	
	public String parseJSON(){
		
		StringBuilder sb = new StringBuilder();
		try {
			String content = new String(Files.readAllBytes(Paths.get(this.path))); // Den gesamten Inhalt der Datei als String speichern
			JSONObject mainJSON = new JSONObject(content); // Nutzung des JSON-Frameworks
			JSONArray flugbuchungarr = mainJSON.getJSONArray("flugbuchung"); // Umwandlung in ein JSONArray, im JSON sind die Eintraege...
																			//... durch geschweifte Klammern voneinander getrennt, jede einzelne Flugbuchung...
																			//... ist ein Index im Array
			
			for(int i = 0; i<flugbuchungarr.length(); i++){ // Ueber alle Eintraege in dem JSONArray
				// Ansprechen der Attribute ueber ihren Namen
				sb.append("Anzahl der Fluggäste : " + flugbuchungarr.getJSONObject(i).get("anzahlGaeste")+"\n");
				sb.append("Gesamtpreis : " + flugbuchungarr.getJSONObject(i).get("preis")+"\n");
				sb.append("Zielflughafen : " +flugbuchungarr.getJSONObject(i).get("zielFlughafen")+"\n");
				sb.append("Handgepäck vorhanden : " +flugbuchungarr.getJSONObject(i).get("isHandgepaeckVorhanden")+"\n");
				sb.append("Abflugdatum : " +flugbuchungarr.getJSONObject(i).get("abflugdatum")+"\n");
				sb.append("Abflugzeit : " +flugbuchungarr.getJSONObject(i).get("abflugzeit")+"\n");
				JSONArray arr = flugbuchungarr.getJSONObject(i).getJSONArray("person"); // ein separates JSONArray im JSONArray, da beliebig viele Personen...
																						// ... in der Datei stehen koennen.
				sb.append("Namen der Fluggäste:\n");
				for(int j = 0; j<arr.length(); j++){
					sb.append("Vorname : " +arr.getJSONObject(j).get("vorname")+"\n");
					sb.append("Nachname : " +arr.getJSONObject(j).get("nachname")+"\n");	
				}
				
				sb.append("Startflughafen : " +flugbuchungarr.getJSONObject(i).get("abflugFlughafen")+"\n");
				sb.append("Telefonnummer : " +flugbuchungarr.getJSONObject(i).get("telefonnr")+"\n");
				sb.append("\n \n");
				
			}
			
			
			return sb.toString();
			
		  
		}
	    catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
}
