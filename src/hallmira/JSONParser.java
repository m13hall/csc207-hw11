package hallmira;

import java.util.ArrayList;

public class JSONParser {
    // Fields
    /**
     * index of the next character to process in input
     */
    int current;
    /**
     * string to parse
     */
    String input;
    /**
     * final object, result of parse
     */
    JSONObject object;

    // Constructor
    public JSONParser(String input) {
	current = 0;
	this.input = input;
	this.object = (JSONObject) this.parse();
    }

    // Methods

    /**
     * converst the current section of string into an appropriate object value
     * 
     * walks through the string, starting at this.current. remembers the
     * character it started at and goes until it finds that character's mate
     * 
     * @pre current is on a { " or [, or the first character of true, false,
     *      null or a number
     * @post current is the value directly after the endMate or the end of true,
     *       false, null or a number
     * @returns the appropriate object represented by the current portion of the
     *          string
     * @returns if the current character is a {, returns an object containing
     *          the specified number of pairs
     * 
     *          if the current character is a
     *          ", returns a string containing any characters before the second "
     * 
     *          if the current character is a [, returns an array containing a
     *          combination of objects, strings, booleans, numbers, or null
     * 
     *          if the current character is a the beginning of true, false, or
     *          null returns the appropriate value
     * 
     *          if the current character is a digit or a -, returns the number
     *          in Double form
     */
    public Object parse() {
	char end = endMate(input.charAt(current));

	switch (end) {

	case '\"': // if its a " , string
	    StringBuffer strResult = new StringBuffer("");
	    this.current++; // advance current past the "
	    while (input.charAt(current) != end) { // add characters
		strResult.append(input.charAt(current));
		this.current++;
	    }// while
	    this.current++; // advance past the second ""
	    return new String(strResult);

	case '}': // if its a bracket, go to object
	    this.current++;
	    JSONObject objResult = new JSONObject(this);
	    while (this.input.charAt(current) != end) {
		while (this.input.charAt(current) == ',' // advance past
							 // whitespace and
							 // commas
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
	    default: // anything else must be a number
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

    /**
     * finds the appropriate character that will close an opening character's
     * structure
     * 
     * @param beginning
     * @return a closing
     */
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

    /**
     * returns the object resulting from a parse of a string
     * 
     * @return object
     */
    public JSONObject get() {
	return this.object;
    } // get
}
