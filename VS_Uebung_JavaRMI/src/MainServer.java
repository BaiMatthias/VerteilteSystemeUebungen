

public class MainServer {
	
	public static void main(String[] args){

        try {
        	
        	WeatherDataServer wds = new WeatherDataServer();
        	wds.startServerService();

        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }
	}

}
