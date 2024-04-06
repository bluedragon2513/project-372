package variables;

import java.util.ArrayList;

import statements.Body;

public class FunctionVar extends Variable<Body> {

	public FunctionVar(String name, Body value, ArrayList<String> parameterNamespace) {
		super(name, value);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isComparable() {
		return false;
	}

	@Override
	public boolean isArithmetic() {
		return false;
	}

	@Override
	public Body getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
