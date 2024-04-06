
public class IntegerVar extends Variable<Integer>
{

	public IntegerVar(String name, Integer value)
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
	public Integer getValue()
	{
		return super.value;
	}
	
}
