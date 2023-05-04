
/**
 * Diese Klasse ist eine Implementierung des IConnector-Interfaces, welches für eine Verbindung ueber den localhost und Port 8181 sicherstellen soll
 * @author Matze
 *
 */
public class LocalConnector implements IConnector {

	@Override
	public int getPort() {
		return 8181;
	}

	@Override
	public String getServer() {
		return "localhost";
	}

}
