import javax.swing.JOptionPane;

public class MainClient {

	public static void main(String[] args) {
		
		String date = JOptionPane.showInputDialog("Bitte Datum eingeben");
		Client c = new Client();
		c.printWeatherData(date);
	}
	
	
}
