package hallmira;

import java.util.ArrayList;

public class JSONParser {
    // Fields

    int current;
    String input;
    JSONObject object;

    // Constructor
    public JSONParser(String input) {
	current = 0;
	this.input = input;
	this.object = new JSONObject(this);
    }

    // Methods

    /**
     * walks through the string, starting at start remembers the character it
     * started at and goes until it finds that character's mate if the next
     * 
     * @pre current is on a { " or [
     * @post current is the value directly after the endMate
     */
    public Object parse() {
	char end = endMate(input.charAt(current));

	switch (end) {
	case '\"': // if its a " , string
	    StringBuffer strResult = new StringBuffer("");
	    this.current++;
	    while (input.charAt(current) != end) {
		strResult.append(input.charAt(current));
	    }// while
	    this.current++;
	    return new String(strResult);
	case '}': // if its a bracket, go to object
	    this.current++;
	    JSONObject objResult = new JSONObject(this);
	    while (this.input.charAt(current) != end) {
		objResult.addPair(this);
	    } // while
	    return objResult;

	case ']': // if its a [ go to array
	    this.current++;
	    ArrayList<JSONObject> arrayResult = new ArrayList<JSONObject>();
	    while (this.input.charAt(current) != end) {
		arrayResult.add((JSONObject) parse());
	    } // while
	    this.current++;
	    return arrayResult;
	} // switch
	return null;
    }// parse

    private char endMate(char beginning) {
	switch (beginning) {
	case '\"':
	    return '\"';
	case '{':
	    return '}';
	case '[':
	    return ']';
	default:
	    return ' ';
	} // switch
    } // endMate

    public JSONObject get(JSONObject object) {
	return this.object;
    } // get
}
