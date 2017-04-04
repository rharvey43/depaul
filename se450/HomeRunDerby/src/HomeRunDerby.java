import java.util.ArrayList;
import java.util.Iterator;

public class HomeRunDerby 
{
    private ArrayList<Pitch>   pitches  = new ArrayList<Pitch>(10);
    private ArrayList<AllStar> allStars = new ArrayList<AllStar>(12);
	
    public static void main(String[] args) 
	{
    	new HomeRunDerby().homeRunDerby();
	}

    public HomeRunDerby()
    {
    	allStars.add(new AllStar("Giancarlo Stanton"));
    	allStars.add(new AllStar("Todd Frazier"));
    	allStars.add(new AllStar("Bobby Abreu"));
    	allStars.add(new AllStar("Joc Pederson"));
    	allStars.add(new AllStar("Josh Hamilton"));
    	allStars.add(new AllStar("David Ortiz"));
    	allStars.add(new AllStar("Robinson Canó"));
    	allStars.add(new AllStar("Yoenis Céspedes"));
    	allStars.add(new AllStar("Adrian Gonzalez"));
    	allStars.add(new AllStar("Mark Trumbo"));
    	allStars.add(new AllStar("Prince Fielder"));
    	allStars.add(new AllStar("Miguel Tejada"));
    	
    	pitches.add(new Pitch("Sinker"));
    	pitches.add(new Pitch("Fast Ball"));
    	pitches.add(new Pitch("Slider"));
    	pitches.add(new Pitch("Curveball"));
    	pitches.add(new Pitch("Cutter"));
    	pitches.add(new Pitch("Breaking Ball"));
    	pitches.add(new Pitch("Knuckle"));
    	pitches.add(new Pitch("Change Up"));
    	pitches.add(new Pitch("Two-Seamer"));
    	pitches.add(new Pitch("Splitter"));
    }
    
    public void homeRunDerby() 
	{
    	Batter homeRunDerbyWinner = null;

    	Iterator<AllStar> allstar = allStars.iterator();
        while (allstar.hasNext())
        {
        	AllStar currentAllStar = allstar.next();
        
    		Batter newBatter = new Batter(currentAllStar.getName());
        	
	    	Iterator<Pitch> pitch = pitches.iterator();
	        while (pitch.hasNext())
	        {
	        	Pitch currentPitch = pitch.next();
	        	
	        	System.out.println("Throwing " + currentPitch + " to batter: " + currentAllStar.getName());
	        	
	        	Swing swing = currentAllStar.swing(currentPitch);
	        	newBatter.addSwing(swing);

	        	System.out.println("It was " + swing);
	        }
	        
	        if (homeRunDerbyWinner == null)
	        {
	        	homeRunDerbyWinner = newBatter;
	        }
	        else
	        {
	        	homeRunDerbyWinner.rank(newBatter);
	        }
        }
    	
        System.out.println(homeRunDerbyWinner);
        
        System.out.println(homeRunDerbyWinner.getHomeRunDerbyWinner());
	}
}
