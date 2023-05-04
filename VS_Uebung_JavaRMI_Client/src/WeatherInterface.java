import java.rmi.RemoteException;
/**
 * Dieses Interface legt fest, welche Methoden der Client verwenden kann, um die Dienste des Servers zu nutzen
 * @author MatzesPC
 *
 */
public interface WeatherInterface {
	public String getWeatherData(String date) throws RemoteException;
}
