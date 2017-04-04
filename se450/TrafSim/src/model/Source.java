/*
 * Decompiled with CFR 0_118.
 */
package model;

import java.util.ArrayList;
import java.util.List;

import managers.ParameterManager;
import model.Agent;
import model.Car;
import model.CarHandler;
import model.CarHandlerList;
import model.Model;
import model.Queue;
import model.MP;

public class Source implements CarHandler,Agent 
{
    private double generationInterval;
    private Queue<Car> carQueue = new Queue<Car>();
    private double length = 0.0;
    private int xposition;
    private int yposition;
    private boolean trafficDirection;
    private CarHandlerList observer;
    private List<Car> cars = new ArrayList<Car>();
    private int carCount = 1;

    Source(int x, int y, boolean direction) 
    {
        xposition = x;
        yposition = y;
        trafficDirection = direction;
        generationInterval = (double)((int)(MP.maxCarGeneration * Math.random())) + MP.minCarGeneration;
    }

    @Override
    public void run(double time) 
    {
        Model model = observer.getObserver();
        if (!carQueue.isEmpty()) 
        {
            moveWaitingCarsAcross();
        }
        if (time % generationInterval == 0.0 && carQueue.size() < 5) 
        {
            Car x = new Car();
            observer.accept(x, model);
            ++carCount;
        }
    }

    @Override
    public void accept(Car d)
{
        d.addObserver(this);
        carQueue.enqueue(d);
        moveWaitingCarsAcross();
    }

    private void moveWaitingCarsAcross() 
    {
        Model model = observer.getObserver();
        List<Car> nextRoadTraffic = observer.getNext(this).getCars();
        if (!nextRoadTraffic.isEmpty()) 
        {
            Car nextCar = nextRoadTraffic.get(nextRoadTraffic.size() - 1);
            double distance = nextCar.getPosition();
            if (distance > ParameterManager.getParameterManager().getCarLength().getMax()) 
            {
                model.getAgents().add(carQueue.peek());
                this.observer.getNext(this).accept(carQueue.dequeue());
            }
        } 
        else 
        {
            model.getAgents().add(carQueue.peek());
            observer.getNext(this).accept(carQueue.dequeue());
        }
    }

    @Override
    public void remove(Car d) 
    {
        if (d == null) 
        {
            throw new IllegalArgumentException();
        }
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

    @Override
    public int vertCompareTo(CarHandler that)
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
    public double getDistancetoNextObstacle(Car car) 
    {
        if (!observer.getNext(this).getCars().isEmpty()) 
        {
            return observer.getNext(this).getCars().get(0).getPosition() + length - car.getPosition() + car.getCarLength();
        }
        return car.getBrakeDistance() + 0.1;
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

