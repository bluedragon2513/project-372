package variables;

public class DoubleVar extends Variable<Double>
{
	public DoubleVar(String name, Double value)
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
	public Double getValue()
	{
		return super.value;
	}
}
