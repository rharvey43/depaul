/*
 * Decompiled with CFR 0_118.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import model.Car;
import model.CarHandler;
import model.CarHandlerList;
import model.Road;

public class Sink implements CarHandler 
{
    private double length = 0.0;
    private int xposition;
    private int yposition;
    private boolean trafficDirection;
    private CarHandlerList observer;
    private List<Car> cars = new ArrayList<Car>();

    Sink(int x, int y, boolean direction) 
    {
        xposition = x;
        yposition = y;
        trafficDirection = direction;
    }

    @Override
    public void accept(Car d) 
    {
        d.addObserver(this);
        remove(d);
    }

    @Override
    public void remove(Car d) 
    {
        if (d == null) 
        {
            throw new IllegalArgumentException();
        }
        d.getOberver().getObservingCarHandlerList().getObserver().getAgents().remove(d);
    }

    @Override
    public List<Car> getCars() 
    {
        return cars;
    }

    public void update(Car car) 
    {
        System.out.println("Update received from Subject, state changed to : " + car.getState());
    }

    @Override
    public int getYPos() 
    {
        return yposition;
    }

    @Override
    public int getXPos() 
    {
        return xposition;
    }

    @Override
    public boolean getDirect() 
    {
        return trafficDirection;
    }

    public int vertCompareTo(Road that) 
    {
        if (yposition < that.getYPos()) 
        {
            return -1;
        }
        if (yposition > that.getYPos()) 
        {
            return 1;
        }
        return 0;
    }

    @Override
    public int horCompareTo(CarHandler that) 
    {
        if (xposition < that.getXPos()) 
        {
            return -1;
        }
        if (xposition > that.getXPos()) 
        {
            return 1;
        }
        return 0;
    }

    @Override
    public void addObserver(CarHandlerList roadlist) 
    {
        observer = roadlist;
    }

    @Override
    public void removeObserver() 
    {
        observer = null;
    }

    @Override
    public CarHandlerList getObservingCarHandlerList() 
    {
        return observer;
    }

    @Override
    public int vertCompareTo(CarHandler that) 
    {
        return 0;
    }

    @Override
    public double getDistancetoNextObstacle(Car car) 
    {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public boolean getState() 
    {
        return true;
    }

    @Override
    public double getLength() 
    {
        return length;
    }
}

