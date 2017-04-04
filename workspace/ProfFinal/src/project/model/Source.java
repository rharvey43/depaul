/*
 * Decompiled with CFR 0_118.
 */
package project.model;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import project.model.Agent;
import project.model.Car;
import project.model.CarHandler;
import project.model.CarHandlerList;
import project.model.MP;
import project.model.Model;
import project.model.Queue;

public class Source
implements CarHandler,
Agent {
    private double generationInterval;
    private Queue<Car> carQueue = new Queue();
    private double length = 0.0;
    private int xposition;
    private int yposition;
    private boolean trafficDirection;
    private CarHandlerList observer;
    private List<Car> _cars = new ArrayList<Car>();
    private int carCount = 1;

    Source(int x, int y, boolean direction) {
        this.xposition = x;
        this.yposition = y;
        this.trafficDirection = direction;
        this.generationInterval = (double)((int)(MP.maxCarGeneration * Math.random())) + MP.minCarGeneration;
    }

    @Override
    public void run(double time) {
        Model model = this.observer.getObserver();
        if (!this.carQueue.isEmpty()) {
            this.moveWaitingCarsAcross();
        }
        if (time % this.generationInterval == 0.0 && this.carQueue.size() < 5) {
            Car x = new Car();
            this.observer.accept(x, model);
            ++this.carCount;
        }
    }

    @Override
    public void accept(Car d) {
        d.addObserver(this);
        this.carQueue.enqueue(d);
        this.moveWaitingCarsAcross();
    }

    private void moveWaitingCarsAcross() {
        Model model = this.observer.getObserver();
        List<Car> nextRoadTraffic = this.observer.getNext(this).getCars();
        if (!nextRoadTraffic.isEmpty()) {
            Car nextCar = nextRoadTraffic.get(nextRoadTraffic.size() - 1);
            double distance = nextCar.getPosition();
            if (distance > MP.maxCarLength) {
                model.getAgents().add(this.carQueue.peek());
                this.observer.getNext(this).accept(this.carQueue.dequeue());
            }
        } else {
            model.getAgents().add(this.carQueue.peek());
            this.observer.getNext(this).accept(this.carQueue.dequeue());
        }
    }

    @Override
    public void remove(Car d) {
        if (d == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<Car> getCars() {
        return this._cars;
    }

    public void update(Car car) {
        System.out.println("Update received from Subject, state changed to : " + car.getState());
    }

    @Override
    public int getYPos() {
        return this.yposition;
    }

    @Override
    public int getXPos() {
        return this.xposition;
    }

    @Override
    public boolean getDirect() {
        return this.trafficDirection;
    }

    @Override
    public int vertCompareTo(CarHandler that) {
        if (this.yposition < that.getYPos()) {
            return -1;
        }
        if (this.yposition > that.getYPos()) {
            return 1;
        }
        return 0;
    }

    @Override
    public int horCompareTo(CarHandler that) {
        if (this.xposition < that.getXPos()) {
            return -1;
        }
        if (this.xposition > that.getXPos()) {
            return 1;
        }
        return 0;
    }

    @Override
    public void addObserver(CarHandlerList roadlist) {
        this.observer = roadlist;
    }

    @Override
    public void removeObserver() {
        this.observer = null;
    }

    @Override
    public CarHandlerList getObservingCarHandlerList() {
        return this.observer;
    }

    @Override
    public double getDistancetoNextObstacle(Car car) {
        if (!this.observer.getNext(this).getCars().isEmpty()) {
            return this.observer.getNext(this).getCars().get(0).getPosition() + this.length - car.getPosition() + car.getCarLength();
        }
        return car.getBreakDistance() + 0.1;
    }

    @Override
    public boolean getState() {
        return true;
    }

    @Override
    public double getLength() {
        return this.length;
    }
}

