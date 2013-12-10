package hallmira;

import java.util.ArrayList;
import hallmira.JSONParser;

public class JSONObject {

    // Fields
    ArrayList<JSONPair> pairs;

    // Constructors
    public JSONObject(String name, Object value) {
	this.pairs.add(new JSONPair(name, value));
    }

    public JSONObject(ArrayList<JSONPair> pairs) {
	this.pairs = pairs;
    }

    public JSONObject(JSONParser parser) {
	this.pairs = new ArrayList<JSONPair>();
    }

    // methods
    public void addPair(JSONParser parser) {
	this.pairs.add(new JSONPair(parser));
    }

    public class JSONPair {
	// Fields
	String name;
	Object value;

	// constructors
	public JSONPair(String name, Object value) {
	    this.name = name;
	    this.value = value;
	}

	public JSONPair(JSONParser parser) {
	    this.name = (String) parser.parse();
	    parser.current++; // skip over the ":"
	    this.value = parser.parse(); // change to look for the right
	}
	
	// methods
	public String name() {
	    return this.name;
	}
	
	public Object value() {
	    return this.value;
	}
    }// JSONPair
} // JSONObject