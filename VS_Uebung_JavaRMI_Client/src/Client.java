import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
	
/**
 * Fragt den Server nach Wetterdaten und uebergibt dabei das Datum
 * Danach Ausgabe und Berechnung der erhaltenen Daten
 * @param date Das Datum, zu dem die Wetterdaten geliefert werden sollen
 */
	public void printWeatherData(String date){
		try {
			WeatherInterface wi = (WeatherInterface)Naming.lookup("//localhost:8181/Server");
			
			String result = wi.getWeatherData(date);
			if(result.equalsIgnoreCase("-1")){ // Falsches Datum
				System.out.println("Keine Temperaturdaten für diese Eingabe vorhanden.");
			}
			else{
				int max = Integer.MIN_VALUE;
				int min = Integer.MAX_VALUE;
				int avg = 0;
				System.out.println("Temperaturdaten:");
				// Trennen der Temperaturen
				String[] resultarr = result.split(",");
				for(int i = 1; i<resultarr.length; i++){ // auf Index 0 liegt das Datum
					System.out.println(i-1 + " Uhr: " + resultarr[i] + " Grad");
					if(max < Integer.parseInt(resultarr[i])){
						max = Integer.parseInt(resultarr[i]); // Extraktion der höchsten Temperatur
					}
					if(min > Integer.parseInt(resultarr[i])){
						min = Integer.parseInt(resultarr[i]); // Extraktion der geringsten Temperatur
					}
					avg += Integer.parseInt(resultarr[i]);
				}
				avg = avg/24; // Durchschnittstemperatur
				System.out.println("Höchsttemperatur: " +max+" Grad");
				System.out.println("Minimaltemperatur "+min+" Grad");
				System.out.println("Durchschnittstemperatur: " +avg + " Grad");
			}
			
			
			
		} catch (MalformedURLException e) {
		
			e.printStackTrace();
		} catch (RemoteException e) {
	
			e.printStackTrace();
		} catch (NotBoundException e) {
			
			e.printStackTrace();
		}
	}

}
