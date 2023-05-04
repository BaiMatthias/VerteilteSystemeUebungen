
public class Main {

	public static void main(String[] args) {
		String path = "C:\\Users\\MatzesPC\\Desktop\\s0552527_MatthiasBaidinger.json";
		JSONFileParser parser = new JSONFileParser(path);
		
		String f = parser.parseJSON();
		System.out.println(f);
	}

}
