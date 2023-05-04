import java.util.Scanner;

public class MainClient {

	public static void main(String[] args) {
		
		Client c = new  Client();
		Scanner s = new Scanner(System.in);
		c.printWeatherData(s.nextLine());

	}

}
