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
import model.LightColor;
import model.Model;
import model.Queue;

public class Light implements CarHandler 
{
    private LightColor lightColor;
    private double length;
    private int xposition;
    private int yposition;
    private boolean trafficDirection;
    private CarHandlerList observer;
    private boolean state;
    private Queue<Car> carQueue = new Queue<Car>();
    private List<Car> cars = new ArrayList<Car>();

    Light(double l) 
    {
        length = l;
    }

    public void run(double time, boolean ewLightState, LightColor ewLightColor) 
    {
        if (!carQueue.isEmpty()) {
            moveWaitingCarsAcross();
        }
        state = ewLightState;
        lightColor = ewLightColor;
    }

    @Override
    public boolean getState() 
    {
        return state;
    }

    @Override
    public void accept(Car d) 
    {
        Model model = this.observer.getObserver();
        d.addObserver(this);
        cars.add(d);
        model.getAgents().remove(d);
        carQueue.enqueue(d);
        moveWaitingCarsAcross();
    }

    private void moveWaitingCarsAcross() 
    {
        Model model = observer.getObserver();
        List<Car> nextRoadTraffic = observer.getNext(this).getCars();
        if (!nextRoadTraffic.isEmpty()) {
            Car nextCar = nextRoadTraffic.get(nextRoadTraffic.size() - 1);
            double distance = nextCar.getPosition() - nextCar.getCarLength();
            if (distance > ParameterManager.getParameterManager().getCarLength().getMax()) 
            {
                cars.remove(carQueue.peek());
                model.getAgents().add(carQueue.peek());
                observer.getNext(this).accept(carQueue.dequeue());
            }
        } 
        else 
        {
            cars.remove(carQueue.peek());
            model.getAgents().add(carQueue.peek());
            observer.getNext(this).accept(carQueue.dequeue());
        }
    }

    public LightColor getColor() 
    {
        return lightColor;
    }

    @Override
    public void addObserver(CarHandlerList carhandlerlist) 
    {
        observer = carhandlerlist;
    }

    @Override
    public List<Car> getCars() 
    {
        return cars;
    }

    @Override
    public boolean getDirect() 
    {
        return trafficDirection;
    }

    @Override
    public double getDistancetoNextObstacle(Car car) 
    {
        if (cars.size() > 1 && cars.indexOf(car) != 0) 
        {
            Car comparingCar = cars.get(cars.indexOf(car) - 1);
            return comparingCar.getPosition() - car.getPosition() - car.getCarLength();
        }
        if (!observer.getNext(this).getCars().isEmpty()) {
            return observer.getNext(this).getCars().get(0).getPosition() + ParameterManager.getParameterManager().getGeneratedRoadLength() - car.getPosition() - car.getCarLength();
        }
        return car.getBrakeDistance() + 0.1;
    }

    @Override
    public CarHandlerList getObservingCarHandlerList() 
    {
        return observer;
    }

    @Override
    public int getXPos() 
    {
        return xposition;
    }

    @Override
    public int getYPos() 
    {
        return yposition;
    }

    @Override
    public int horCompareTo(CarHandler that) 
    {
        if (this.xposition < that.getXPos()) 
        {
            return -1;
        }
        if (this.xposition > that.getXPos()) 
        {
            return 1;
        }
        return 0;
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
    public void removeObserver() 
    {
        observer = null;
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
    public double getLength() 
    {
        return length;
    }
}

