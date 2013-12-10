package hallmira;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedReader;

public class JSONExpt {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
	PrintWriter pen = new PrintWriter(System.out, true);
	String toParse = "{\"contruct\":\"value\",\"array\":[{\"Mira\":{\"middle\":\"elise\",\"last\":\"hall\"}}, {}, \"string\", [{\"Mira\":{\"middle\":\"elise\",\"last\":\"hall\"}}], true, null, false, -56.4],\"construct\":\"another\", \"object\":{\"andrew\":\"kelley\", \"mira\":\"hall\"}}";
	JSONParser thing = new JSONParser(toParse);
	pen.println((thing.object).toString());
	BufferedReader json = new BufferedReader(new FileReader("/Users/southpaw14/Documents/GitHub/csc207/csc207-hw11/src/hallmira/json.txt"));
	String input = json.readLine();
	json.close();
	JSONParser thing2 = new JSONParser(input);
	pen.println((thing2.object).toString());
    }

}
