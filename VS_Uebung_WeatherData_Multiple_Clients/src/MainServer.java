import java.io.IOException;

public class MainServer {

	public static void main(String[] args) {
		IConnector connector = new LocalConnector();
		try {
			MultiThreadServer mts = new MultiThreadServer(connector.getPort());
			mts.runService();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
