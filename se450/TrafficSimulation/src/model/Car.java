package model;

//import managers.ParameterManager;
import managers.randomParaManager;

/**
 * A car remembers its position from the beginning of its road.
 * Cars have random velocity and random movement pattern:
 * when reaching the end of a road, the dot either resets its position
 * to the beginning of the road, or reverses its direction.
 */
public class Car implements Agent 
{
	Car() { } // Created only by this package

	private double position = 0;
	randomParaManager r = new randomParaManager();
	//private double velocity = (int) Math.ceil(Math.random() * MP.maxVelocity);
	private double velocity = r.getRandCarMaximumVelocity();
	private java.awt.Color color = new java.awt.Color((int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255));

	public double getPosition() 
	{
		return position;
	}
	public double getVelocity()
	{
		return velocity;
	}
	public java.awt.Color getColor() 
	{
		return color;
	}
	public void run(double time) 
	{
		//if ((position + velocity) > (r.getRandRoadSegmentLength()-r.getRandCarLength()))
		if ((position + velocity) > (MP.roadLength-MP.carLength))
			position = 0;
		
		position += velocity;
	}
}
