package hallmira;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.io.BufferedReader;

public class JSONExpt {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
	PrintWriter pen = new PrintWriter(System.out, true);

	String toParse = "{\"contruct\":\"value\",\"array\":[{\"Mira\":{\"middle\":\"elise\",\"last\":\"hall\"}}, {}, \"string\", [{\"Mira\":{\"middle\":\"elise\",\"last\":\"hall\"}}], true, null, false, -56.4],\"construct\":\"another\", \"object\":{\"andrew\":\"kelley\", \"mira\":\"hall\"}}";
	JSONParser thing = new JSONParser(toParse);
	pen.println((thing.object).toString());

	BufferedReader json = new BufferedReader(new FileReader(
		"/home/hallmira/csc207/git/csc207-hw11/src/hallmira/json.txt"));
	String input = json.readLine();
	json.close();

	JSONParser thing2 = new JSONParser(input);
	String result = (thing2.object).toString();
	pen.println(result);
	String resultTrim = result.replaceAll("\\s+", "");
	String inputTrim = input.replaceAll("\\s+", "");
	pen.println(resultTrim.equals(inputTrim));

	for (int i = 0; i < result.length() && i < input.length(); i++) {
	    if (result.charAt(i) != input.charAt(i)) {
		pen.println("differ at: " + i);
		pen.println("result: " + result.substring(i - 10, i) + " *"
			+ result.charAt(i) + "* "
			+ result.substring(i + 1, i + 10));
		pen.println("input: " + input.substring(i - 10, i) + " *"
			+ input.charAt(i) + "* "
			+ input.substring(i + 1, i + 10));
		pen.println();
		break;
	    } // if
	}// for

	/*
	 * the above experiment reveals that the reason the input and output
	 * strings don't match up is simply spacing on the part of the input and
	 * not a result of actual differences in objects
	 * 
	 * specifically, the code never has spaces after commas, but the built
	 * in toString for arrays puts them in, so the result has them
	 */
    } // main

}// JSONExpt
