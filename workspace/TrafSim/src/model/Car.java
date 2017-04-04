/*
 * Decompiled with CFR 0_118.
 */
package model;

import java.awt.Color;

import managers.ParameterManager;
import model.Agent;
import model.CarHandler;

public class Car
implements Agent {
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
        velocity = maxVelocity = Math.random() * (ParameterManager.getParameterManager().getCarMaximumVelocity().getMax() - ParameterManager.getParameterManager().getCarMaximumVelocity().getMin()) + ParameterManager.getParameterManager().getCarMaximumVelocity().getMin();
    	brakeDistance = Math.random() * (ParameterManager.getParameterManager().getCarBrakeDistance().getMax() - ParameterManager.getParameterManager().getCarBrakeDistance().getMin()) + ParameterManager.getParameterManager().getCarBrakeDistance().getMin();
        stopDistance = Math.random() * (ParameterManager.getParameterManager().getCarStopDistance().getMax() - ParameterManager.getParameterManager().getCarStopDistance().getMin()) + ParameterManager.getParameterManager().getCarStopDistance().getMin();
        color = new Color((int)Math.ceil(Math.random() * 255.0), (int)Math.ceil(Math.random() * 255.0), (int)Math.ceil(Math.random() * 255.0));
        maxVelocity = Math.random() * (ParameterManager.getParameterManager().getCarMaximumVelocity().getMax() - ParameterManager.getParameterManager().getCarMaximumVelocity().getMin()) + ParameterManager.getParameterManager().getCarMaximumVelocity().getMin();
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

    public Color getColor() 
    {
        return color;
    }

    @Override
    public void run(double time) 
    {
        checkTailGate();
        checkPosOnRoad();
        position += velocity;
    }

    public void checkTailGate() 
    {
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

    public void checkPosOnRoad() 
    {
        if (this.getPosition() > this.observer.getLength() - carLength) 
        {
            CarHandler nextCarHandler = observer.getObservingCarHandlerList().getNext(observer);
            if (nextCarHandler != this.observer) 
            {
                if (nextCarHandler.getState()) 
                {
                    observer.remove(this);
                    nextCarHandler.accept(this);
                    position = 0.0;
                }
            } 
            else 
            {
                velocity = 0.0;
                observer.remove(this);
            }
        }
    }

    public String getState() 
    {
        return state;
    }

    public void addObserver(CarHandler carhandler) 
    {
        observer = carhandler;
    }

    public CarHandler getOberver() 
    {
        return observer;
    }

    public void removeObserver() 
    {
        observer = null;
    }

    public double getCarLength() 
    {
        return carLength;
    }

    public double getBrakeDistance() 
    {
        return brakeDistance;
    }

    public void setPosition(double pos) 
    {
        position = pos;
    }

    public void setSpeed(double speed) 
    {
        maxVelocity = speed;
    }
}

