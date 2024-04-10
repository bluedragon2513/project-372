package variables;

public class StringVar extends Variable<String>
{

	public StringVar(String name, String value)
	{
		super(name, value);
	}

	@Override
	public boolean isComparable()
	{
		return false;
	}

	@Override
	public boolean isArithmetic()
	{
		return false;
	}

	@Override
	public String getValue()
	{
		return super.value;
	}
	
}
