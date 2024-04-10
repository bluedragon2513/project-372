package variables;

public class BooleanVar extends Variable<Boolean>
{
	public BooleanVar(String name, Boolean value)
	{
		super(name, value);
	}

	@Override
	public boolean isComparable()
	{
		return true;
	}

	@Override
	public boolean isArithmetic()
	{
		return true;
	}

	@Override
	public Boolean getValue()
	{
		return super.value;
	}
}
