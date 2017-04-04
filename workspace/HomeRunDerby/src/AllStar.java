public class AllStar 
{
	private final String name;
	
	public AllStar(final String sName)
	{
		name = sName;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Swing swing(Pitch pitch)
	{
		return (Math.random() >= pitch.getHomeRunHitAbility() ? new HomeRun(pitch.getPitchCount()) : new Out(pitch.getPitchCount()));
	}
}
 