
public class Main {

	public static void main(String[] args) {
		String path = "C:\\Users\\MatzesPC\\Desktop\\s0552527_MatthiasBaidinger.xml";
		
		XMLFileParser parser = new XMLFileParser(path);
		
		String result = parser.parseXML();
		if(result != null){
			System.out.println(result);
		}
		

	}

}
