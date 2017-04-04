//you can change the class attributes as necessary
public final class ImmutableMirroredInteger
{
	//you must keep the type but you can change any attributes and assignment necessary
	private  final MutableMirroredInteger mutableMirroredInteger;
	
	public ImmutableMirroredInteger(MutableMirroredInteger value)
	{
		//you may change this logic but not the signature of the constructor
		mutableMirroredInteger = new MutableMirroredInteger(value.getValue());
	}
	
	public ImmutableMirroredInteger(int value)
	{
		//you may change this logic but not the signature of the constructor
		mutableMirroredInteger = new MutableMirroredInteger(value);
	}
	
	public final void change(int value)
	{
		//you may change this return type and logic - but not the signature of the constructor
		 new ImmutableMirroredInteger(new MutableMirroredInteger(value));
	}
	
	//you may change these return type and logic - but not the signature of the method
	public final int getValue()
	{
		return getCopyValue(mutableMirroredInteger).getValue();
	}
	
	private MutableMirroredInteger getCopyValue(MutableMirroredInteger value)
	{
		MutableMirroredInteger copy = value;
		return copy;
	}
}
