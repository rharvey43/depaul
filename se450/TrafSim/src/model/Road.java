/*
 * Decompiled with CFR 0_118.
 */
package model;

import java.util.ArrayList;
import java.util.List;

import managers.ParameterManager;
import model.Car;
import model.CarHandler;
import model.CarHandlerList;

public class Road implements CarHandler 
{
    private double length = Math.random() * (ParameterManager.getParameterManager().getRoadSegmentLength().getMax() - ParameterManager.getParameterManager().getRoadSegmentLength().getMin()) + ParameterManager.getParameterManager().getRoadSegmentLength().getMin();
    private int xposition;
    private int yposition;
    private boolean trafficDirection;
    private CarHandlerList observer;
    private List<Car> cars = new ArrayList<Car>();

    Road(int x, int y, boolean direction) 
    {
        xposition = x;
        yposition = y;
        trafficDirection = direction;
    }

    @Override
    public void accept(Car d) 
    {
        if (d == null) 
        {
            throw new IllegalArgumentException();
        }
        cars.add(d);
        d.addObserver(this);
    }

    @Override
    public void remove(Car d) 
    {
        if (d == null) 
        {
            throw new IllegalArgumentException();
        }
        cars.remove(d);
        d.removeObserver();
    }

    @Override
    public List<Car> getCars() 
    {
        return cars;
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
        if (yposition < that.yposition) 
        {
            return -1;
        }
        if (yposition > that.yposition) 
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
        if (cars.size() > 1 && cars.indexOf(car) != 0) 
        {
            Car comparingCar = cars.get(cars.indexOf(car) - 1);
            return comparingCar.getPosition() - car.getPosition() - car.getCarLength();
        }
        if (length - car.getPosition() - car.getCarLength() <= car.getBrakeDistance() && !observer.getNext(this).getState()) 
        {
            return length - car.getPosition() - car.getCarLength();
        }
        if (!observer.getNext(observer.getNext(this)).getCars().isEmpty()) 
        {
            return observer.getNext(observer.getNext(this)).getCars().get(0).getPosition() + length - car.getPosition() - car.getCarLength();
        }
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

