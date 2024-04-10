package variables;

public abstract class Variable<T>
{
	protected String name;
	protected T value;
	
	public Variable(String name, T value)
	{
		this.name = name;
		this.value = value;
	}
	
	public abstract boolean isComparable();
	public abstract boolean isArithmetic();
	public abstract T getValue();
}
