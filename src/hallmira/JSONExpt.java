package hallmira;

import java.io.PrintWriter;

public class JSONExpt {

    /**
     * @param args
     */
    public static void main(String[] args) {
	PrintWriter pen = new PrintWriter(System.out, true);
	String toParse = "{\"contruct\":\"value\"}";
	JSONParser thing = new JSONParser(toParse);
	pen.println("made it through first test?");
    }

}
