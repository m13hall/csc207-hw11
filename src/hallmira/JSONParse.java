package hallmira;

public class JSONParse {

    // Fields
    int index; // integer to use as index
    String input; // input string to parse
    int length; // length of input

    // Constructor
    public JSONParse(String str) {
	this.index = 0;
	this.input = str;
	this.length = this.input.length();
    } // JSONParse(String)

    // Methods
    public void parse() {
	char ch = this.input.charAt(this.index);
	char end = endMate(ch);
	while ((this.index < this.length) && (ch != end)) {
	    switch (ch) {
	    case '\"':
		this.index++;
		parseString();
		break;
	    case '{':
		break;
	    case '[':
		break;
	    default:
		break;

	    }
	} // while
    } // parse()

    public String parseString() {
	String result = "";
	char ch = this.input.charAt(this.index);
	while ((this.index < this.length) && (ch != '\"')) {
	    result += ch;
	} // while
	this.index++;
	return result;
    } // parseString()

    public Object parseObject(Object object) {
	char ch = this.input.charAt(this.index);
	char end = endMate(ch);
	String name = this.parseString();
	while ((this.index < this.length) && (ch != end)) {
	    switch (ch) {
	    case '\"':
		this.index++;
		this.parseString();
		
		break;
	    case '{':
		break;
	    case '[':
		break;
	    case ':' :
		this.index++;
		break;
	    default:
		break;
	    }
	}
    }

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

} // class JSONParse
