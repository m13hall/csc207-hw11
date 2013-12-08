package hallmira;

import java.util.ArrayList;

public class JSONObjects {
    
    ArrayList<JSONObject> objects;
    
    public JSONObjects(String input) {
	this.objects = parse(input);
    }
    public static ArrayList<JSONObject> parse(String input) {
	//look for object
	//add object
	//if value is an object, call again
    }
    public class JSONObject {
	    String name;
	    Object value;
	    
	    public JSONObject(String name, Object Value) {
		this.name = name;
		this.value = value;
	    }
	}
    
}
