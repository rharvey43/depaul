
public class Immutable
{
	public static void main(String[] args) 
	{
		new Immutable().immutableTest();
	}
	
	public void immutableTest()
	{
		MutableMirroredInteger mutableMirroredInteger = new MutableMirroredInteger(1);
		
		ImmutableMirroredInteger immutableMirroredInteger = new ImmutableMirroredInteger(mutableMirroredInteger);
		
		//to enable assertion -- Menu --> Run --> DEBUG configuration --> Arguments --> VM arguments --> add '-ea' inside the VM arguments box
		assert(mutableMirroredInteger.getValue() == 1);

		assert(immutableMirroredInteger.getValue() == 1);
		
		mutableMirroredInteger.change(2);
		
		assert(mutableMirroredInteger.getValue() == 2);

		assert(immutableMirroredInteger.getValue() == 1);

		immutableMirroredInteger.change(3);

		assert(immutableMirroredInteger.getValue() == 1);
	}
}
