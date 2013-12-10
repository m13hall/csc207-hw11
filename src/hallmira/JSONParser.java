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
	this.object = (JSONObject) this.parse();
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
	    while (this.input.charAt(current) != end) {
		if (this.input.charAt(current) == '\\') {
		    if (this.input.charAt(current + 1) == '\"') {
			strResult.append("\\\"");
			this.current += 2;
		    } else {
			strResult.append(input.charAt(current));
			this.current++;
		    } // if/else
		} else {
		    strResult.append(input.charAt(current));
		    this.current++;
		} // if/else
	    }// while
	    this.current++;
	    return new String(strResult);
	case '}': // if its a bracket, go to object
	    this.current++;
	    JSONObject objResult = new JSONObject(this);
	    while (this.input.charAt(current) != end) {
		while (this.input.charAt(current) == ','
			|| this.input.charAt(current) == ' ') {
		    this.current++;
		} // while for whitespace
		objResult.addPair(this);
	    } // while
	    this.current++;
	    return objResult;

	case ']': // if its a [ go to array
	    this.current++;
	    ArrayList<Object> arrayResult = new ArrayList<Object>();
	    while (this.input.charAt(current) != end) {
		while (this.input.charAt(current) == ','
			|| this.input.charAt(current) == ' ') {
		    this.current++;
		} // while for whitespace
		if (this.input.charAt(current) == '\"') {
		    arrayResult.add((String) parse());
		} else {
		    arrayResult.add((Object) parse());
		}
	    } // while
	    this.current++;
	    return arrayResult;
	case ' ': // if the character wasn't a special character
	    switch (this.input.charAt(this.current)) {
	    case 't':
		this.current += 4;
		return true;
	    case 'f':
		this.current += 5;
		return false;
	    case 'n':
		this.current += 4;
		return null;
	    default:
		if (Character.isDigit(this.input.charAt(this.current))
			|| this.input.charAt(this.current) == '-') {
		    String result = "";
		    while (Character.isDigit(this.input.charAt(this.current))
			    || this.input.charAt(this.current) == '.'
			    || this.input.charAt(this.current) == '-') {
			result = result + this.input.charAt(this.current);
			this.current++;
		    }// while
		    return new Double(result);
		} else {
		    System.out.println("Error: incorrect input: "
			    + this.input.charAt(this.current));
		    this.current++;
		}
	    } // switch
	}// switch
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
