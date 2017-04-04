import java.util.ArrayList;
import java.util.Iterator;

//TODO add any imports as needed

public class Batter
{
	private final String name;
	private Batter higher = null;
	private Batter lower = null;
	private static String batterTree = "";
	private static String winner = "";
	private Batter winBat = null;
	private ArrayList<Swing> outList = new ArrayList<Swing>();
	private ArrayList<Swing> homerunList = new ArrayList<Swing>();

	public Batter(String sName)
	{
		name = sName;
	}
	
	public void addSwing(Swing swing)
	{
		if (swing instanceof HomeRun)
		{
			homerunList.add(swing);
		}
		else if (swing instanceof Out)
		{
			outList.add(swing);
		}
	}

	public void rank(Batter batter)
	{
		
		if(rankBatter(batter) < 0) //batter is greater than this
		{
			if (higher != null)
			{
				if(higher.rankBatter(batter) < 0) //batter is greater than higher and this
				{
					higher.rank(batter);
				}
				else //batter is less than higher but greater than this
				{
					batter.higher = higher;
					batter.lower = this;
					higher.lower = batter;
					this.higher = batter;
					
				}
			}
			else //batter is greater than this and higher doesn't exist
			{
				this.higher = batter;
				batter.lower = this;
				
				
			}
		}
		else if(rankBatter(batter) > 0) //this is greater than batter
		{
			if (lower != null)
			{
				if(lower.rankBatter(batter) > 0) //batter is less than lower and this
				{
					lower.rank(batter);
				}
				else //batter is greater than lower but less than this
				{
					lower.higher = batter;
					batter.lower = lower;
					batter.higher = this;
					this.lower = batter;
					
				}
			}
			else //this is greater than batter and lower doesn't exist
			{
				lower = batter;
				batter.higher = this;
			}
		}
			
	}
	
	public int rankBatter(Batter batter)
	{
		if (this.homerunList.size() > batter.homerunList.size()) //this is greater than batter
		{
			//System.out.println(this.name + "_" + this.homerunList.size() + " > " + batter.name + " " + batter.homerunList.size());
			return 1;
		}
		else if (this.homerunList.size() < batter.homerunList.size()) //this is less than batter
		{
			//System.out.println(this.name + "_" + this.homerunList.size() + " < " + batter.name + " " + batter.homerunList.size());
			return -1;
		}
		else
		{
			if (this.name.compareTo(batter.name) < 0)
			{
				//System.out.println(this.name + " " + batter.name + " " + this.homerunList.size() + " >= " + batter.homerunList.size());
				return 1;
			}
			else 
			{
				//System.out.println(this.name + " " + batter.name + " " + this.homerunList.size() + " <= " + batter.homerunList.size());
				return -1;
			}
		}
	}
	
	@Override
	public String toString()
	{
		
		if (higher != null)
		{
			higher.toString();
		}
		else
		{
			winBat = this;
		}
		if (winBat != null)
		{
			while (winBat != null)
			{
				batterTree += "Batter: " + winBat.name + " had " + winBat.homerunList.size() + " homeruns.\n";
				winBat = winBat.lower;
			}
		}
			
		return batterTree;
	}

	public String getHomeRunDerbyWinner()
	{
		String pitchCount = "";
		if (higher != null)
		{
			higher.getHomeRunDerbyWinner();
		}
		else
		{
			winBat = this;
		}
		if (winBat != null)
		{
			Iterator<Swing> homeRun = winBat.homerunList.iterator();
			while (homeRun.hasNext())
			{
				pitchCount += ("[" + homeRun.next().getSwingCount() + "]");
			}
			winner += "Batter : " + winBat.name + " is the Home Run Derby Winner. He hit homeruns on pitches: " + pitchCount;
			
		}
		return winner;
	}
}
