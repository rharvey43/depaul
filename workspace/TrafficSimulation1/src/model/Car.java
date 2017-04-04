package model;

import java.awt.Color;

import managers.ParameterManager;
//import managers.ParameterManager;
import project.model.CarHandler;
import project.model.MP;

/**
 * A car remembers its position from the beginning of its road.
 * Cars have random velocity and random movement pattern:
 * when reaching the end of a road, the dot either resets its position
 * to the beginning of the road, or reverses its direction.
 */
public class Car implements Agent 
{
    private String state = "";
    private CarHandler observer;
    private double position = 0.0;
    private double carLength = (double)((int)(ParameterManager.getParameterManager().getCarLength().getMax() * Math.random())) + ParameterManager.getParameterManager().getCarLength().getMin();
    private double maxVelocity;
    private double velocity;
    private double brakeDistance;
    private double stopDistance;
    private Color color;
	
	Car() 
	{
		maxVelocity = Math.random() * (ParameterManager.getParameterManager().getCarMaximumVelocity().getMax() - ParameterManager.getParameterManager().getCarMaximumVelocity().getMin()) + ParameterManager.getParameterManager().getCarMaximumVelocity().getMin();
		velocity = maxVelocity;
        brakeDistance = Math.random() * (ParameterManager.getParameterManager().getCarBrakeDistance().getMax() - ParameterManager.getParameterManager().getCarBrakeDistance().getMin()) + ParameterManager.getParameterManager().getCarBrakeDistance().getMin();
        stopDistance = Math.random() * (ParameterManager.getParameterManager().getCarStopDistance().getMax() - ParameterManager.getParameterManager().getCarStopDistance().getMin()) + ParameterManager.getParameterManager().getCarStopDistance().getMin();
        color = new Color((int)Math.ceil(Math.random() * 255.0), (int)Math.ceil(Math.random() * 255.0), (int)Math.ceil(Math.random() * 255.0));
    }
	public double getPosition()
	{
		return position;
	}
	public double getNextPosition() 
	{
        return position += velocity;
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
	public void checkStop() {
        double distanceBetween = observer.getDistancetoNextObstacle(this);
        if (distanceBetween <= brakeDistance) 
        {
            if (distanceBetween <= stopDistance) 
            {
                velocity *= 0;
            } 
            else 
            {
                velocity *= .5;
                if (velocity >= distanceBetween) 
                {
                    velocity = ParameterManager.getParameterManager().getCarMaximumVelocity().getMin();
                }
            }
        } 
        else 
        {
            velocity = velocity == 0.0 ? maxVelocity / 2.0 : maxVelocity;
        }
    }
}
