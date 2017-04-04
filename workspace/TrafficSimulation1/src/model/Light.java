package model;

/**
 * A light has a boolean state.
 */
public class Light implements Agent 
{
	Light() { } // Created only by this package

	private String state;

	public String getState() 
	{
		return state;
	}
	public void run(double time) 
	{
		if (time%40 >= 10) 
		{
			state = "Red";
		}
		else if (time%40 >= 5)
		{
			state = "Yellow";
		}
		else
		{
			state = "Green";
		}
	}
}

