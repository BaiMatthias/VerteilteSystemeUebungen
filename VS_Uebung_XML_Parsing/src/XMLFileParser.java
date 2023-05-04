import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 * Diese Klasse repraesentiert einen XML-Parser, der XML-Dateien von Flugbuchungen auslesen  kann
 * @author MatzesPC
 *
 */
public class XMLFileParser {
	private String path;
	
	public XMLFileParser(String path){
		this.path = path;
	}
	/**
	 * Oeffnet die XML-Datei, liest diese aus und gibt den Inhalt als String zueruck
	 * @return Der ausgelesene Inhalt
	 */
	public String parseXML(){
		try {
		File xmlFile = new File(this.path);
		StringBuilder sb = new StringBuilder();
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance(); // Factory - Methode, um ein Document zu erstellen
	
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder(); 
			Document doc = docBuilder.parse(xmlFile); // parsen der Datei, um diese auslesen zu koennen
			doc.getDocumentElement().normalize(); // empfohlen https://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			
			NodeList flugnodeList = doc.getElementsByTagName("buchung"); // Root - Node ansprechen
			for(int i = 0; i< flugnodeList.getLength(); i++){ // schleife ueber alle Subnodes
				
				Node flugnode = flugnodeList.item(i); // Objekt aus Subnode erstellen
				
				 
				if (flugnode.getNodeType() == Node.ELEMENT_NODE) { // Wichtig fuer naechsten Befehl

					Element flugElement = (Element) flugnode; // Konvertierung in ein Element, um Zugriff auf die Daten zu haben

					// Auslesen des Inhalts anhand der Tags der Elemente
					sb.append("Buchungs-ID : " + flugElement.getAttribute("id")+"\n");
					sb.append("Anzahl der Fluggäste : " + flugElement.getElementsByTagName("anzahlGaeste").item(0).getTextContent()+"\n");
					sb.append("Gesamtpreis : " + flugElement.getElementsByTagName("preis").item(0).getTextContent()+"\n");
					sb.append("Zielflughafen : " + flugElement.getElementsByTagName("zielFlughafen").item(0).getTextContent()+"\n");
					sb.append("Handgepäck vorhanden : " + flugElement.getElementsByTagName("isHandgepaeckVorhanden").item(0).getTextContent()+"\n");
					sb.append("Abflugdatum : " + flugElement.getElementsByTagName("abflugdatum").item(0).getTextContent()+"\n");
					sb.append("Abflugzeit : " + flugElement.getElementsByTagName("abflugzeit").item(0).getTextContent()+"\n");
					NodeList personNodeList = flugElement.getElementsByTagName("person"); // Es koennen beliebig viele Personen in der XML-Datei gefuehrt werden
					sb.append("Namen der Fluggäste:\n");
					for(int j = 0; j < personNodeList.getLength(); j++){ // Ueber alle Personen im jeweiligen Subnode
						Node personNode = personNodeList.item(j);
						Element personElement = (Element) personNode;
						sb.append("Vorname : " + personElement.getElementsByTagName("vorname").item(0).getTextContent()+"\n");
						sb.append("Nachname : " + personElement.getElementsByTagName("nachname").item(0).getTextContent()+"\n");
					}
					sb.append("Startflughafen : " + flugElement.getElementsByTagName("abflugFlughafen").item(0).getTextContent()+"\n");
					sb.append("Telefonnummer : " + flugElement.getElementsByTagName("telefonnr").item(0).getTextContent()+"\n");
					sb.append("\n\n");
					
				}
				
			}
			
			return sb.toString();
			
			
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
