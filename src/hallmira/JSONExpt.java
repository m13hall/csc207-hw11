package hallmira;

import java.io.PrintWriter;
import java.util.Arrays;

public class JSONExpt {

    /**
     * @param args
     */
    public static void main(String[] args) {
	PrintWriter pen = new PrintWriter(System.out, true);
	String toParse = "{\"contruct\":\"value\",\"array\":[{\"Mira\":{\"middle\":\"elise\",\"last\":\"hall\"}}, {}, \"string\", [{\"Mira\":{\"middle\":\"elise\",\"last\":\"hall\"}}], true, null, false, -56.4],\"construct\":\"another\", \"object\":{\"andrew\":\"kelley\", \"mira\":\"hall\"}}";
	JSONParser thing = new JSONParser(toParse);
	pen.println((thing.object).toString());
    }

}
