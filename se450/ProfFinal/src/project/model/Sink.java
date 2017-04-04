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
import project.model.Model;
import project.model.Road;

public class Sink
implements CarHandler {
    private double length = 0.0;
    private int xposition;
    private int yposition;
    private boolean trafficDirection;
    private CarHandlerList observer;
    private List<Car> _cars = new ArrayList<Car>();

    Sink(int x, int y, boolean direction) {
        this.xposition = x;
        this.yposition = y;
        this.trafficDirection = direction;
    }

    @Override
    public void accept(Car d) {
        d.addObserver(this);
        this.remove(d);
    }

    @Override
    public void remove(Car d) {
        if (d == null) {
            throw new IllegalArgumentException();
        }
        d.getOberver().getObservingCarHandlerList().getObserver().getAgents().remove(d);
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

    public int vertCompareTo(Road that) {
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
    public int vertCompareTo(CarHandler that) {
        return 0;
    }

    @Override
    public double getDistancetoNextObstacle(Car car) {
        return Double.POSITIVE_INFINITY;
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

