public class Pitch 
{
	private double percentage = Math.random();
	private final String pitchName;
	private static int pitches = 0;
	private int pitchCount = 0;
	
	public Pitch(final String sPitchName)
	{
		++pitches;
		
		pitchCount = pitches;
		
		pitchName = sPitchName; 
	}
	
	public int getPitchCount() { return pitchCount; }
	
	public double getHomeRunHitAbility() { return percentage; }
	
	public String toString() { return "Pitch #" + pitchCount + " : " + pitchName; }
}
