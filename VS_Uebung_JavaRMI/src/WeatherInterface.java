import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Dieses Interface legt die Methoden fest, die der Server implementieren muss, um seine Dienste remote abrufbar machen zu koennen
 *
 * @author MatzesPC
 *
 */
public interface WeatherInterface extends Remote {

	public String getWeatherData(String date) throws RemoteException;
}
