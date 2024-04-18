package control_structure;

/**
 * This class acts to control the Return Statement
 * 	* only Functions may unpack ReturnValue objects with getValue
 * 
 * @author Anthony Nguyen
 */
public class ReturnValue {
	Object value;
	public ReturnValue(Object value) {
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
}
