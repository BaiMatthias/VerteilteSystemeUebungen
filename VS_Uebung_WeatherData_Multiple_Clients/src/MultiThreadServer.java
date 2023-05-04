import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Diese Klasse repraesentiert einen Server, mit dem sich mehrere Clients verbinden koennen und Wetterdaten zu einem Datum zur Verfuegung stellt.
 * 
 * @author Matze
 *
 */
public class MultiThreadServer {

	
	private final int MAX_CLIENTS = 5;
	private final ServerServiceThread[] threadArr = new ServerServiceThread[MAX_CLIENTS]; // Array fuer jeden Thread
	private final ServerSocket server;
	
	public ServerSocket getSocket(){
		return this.server;
	}
	public int getMax_Clients(){
		return MAX_CLIENTS;
	}
	
	
	public MultiThreadServer(int port) throws IOException{
		this.server = new ServerSocket(port);
		
	}
	/**
	 * Startet den Server und wartet auf Clients, die sich verbinden woll
	 * Jeder Client wird in einem Thread behandelt, Anfragen werden parallel behandelt
	 */
	public void runService(){
		
		try {
			
			    
			    while(true){
			    	System.out.println("Verbindung wird aufgebaut...");
					Socket clientSocket = this.server.accept(); // Verbindung mit Client
					int i = 0;
					for(i = 0; i < this.MAX_CLIENTS; i++){
						if(this.threadArr[i] == null){
							System.out.println("Verbindung wurde aufgebaut!");
							this.threadArr[i] = new ServerServiceThread(clientSocket); // Client-Server-Verbindung wird in eigenem Thread ausgelagert
							System.out.println("Neuer Client:");
							System.out.println("Adresse: " + clientSocket.getInetAddress());
							System.out.println("Port: " + clientSocket.getPort());				
							System.out.println("Thread-ID: " + this.threadArr[i].getId());
							this.threadArr[i].start(); // Start des Threads, damit Anfrage des Clients bearbeitet werden kann
							break;
						}
					}
					if(i == this.MAX_CLIENTS){ 
						PrintWriter pw = new PrintWriter(clientSocket.getOutputStream());
				        pw.println("Zuviele Clients verbunden!");
				        pw.close();
				        clientSocket.close();
					}
					
			    }
				
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	

}
