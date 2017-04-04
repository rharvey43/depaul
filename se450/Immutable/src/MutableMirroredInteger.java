//you may not change this class or anything in it
public class MutableMirroredInteger
{
	private int [] data = new int[2];
	
	public MutableMirroredInteger(int value)
	{
		data[0] = data[1] = value;
	}
	
	public void change(int value)
	{
		data[0] = data[1] = value;
	}

	public int getValue()
	{
		return data[0];
	}
}
