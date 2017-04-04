/*
 * Decompiled with CFR 0_118.
 */
package project.model;

import java.util.ArrayList;
import java.util.List;
import project.model.Car;
import project.model.CarHandler;
import project.model.CarHandlerList;
import project.model.MP;

public class Road
implements CarHandler {
    private double length = Math.random() * (MP.maxroadLength - MP.minroadLength) + MP.minroadLength;
    private int xposition;
    private int yposition;
    private boolean trafficDirection;
    private CarHandlerList observer;
    private List<Car> _cars = new ArrayList<Car>();

    Road(int x, int y, boolean direction) {
        this.xposition = x;
        this.yposition = y;
        this.trafficDirection = direction;
    }

    @Override
    public void accept(Car d) {
        if (d == null) {
            throw new IllegalArgumentException();
        }
        this._cars.add(d);
        d.addObserver(this);
    }

    @Override
    public void remove(Car d) {
        if (d == null) {
            throw new IllegalArgumentException();
        }
        this._cars.remove(d);
        d.removeObserver();
    }

    @Override
    public List<Car> getCars() {
        return this._cars;
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
        if (this.yposition < that.yposition) {
            return -1;
        }
        if (this.yposition > that.yposition) {
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
        if (this._cars.size() > 1 && this._cars.indexOf(car) != 0) {
            Car comparingCar = this._cars.get(this._cars.indexOf(car) - 1);
            return comparingCar.getPosition() - car.getPosition() - car.getCarLength();
        }
        if (this.length - car.getPosition() - car.getCarLength() <= car.getBreakDistance() && !this.observer.getNext(this).getState()) {
            return this.length - car.getPosition() - car.getCarLength();
        }
        if (!this.observer.getNext(this.observer.getNext(this)).getCars().isEmpty()) {
            return this.observer.getNext(this.observer.getNext(this)).getCars().get(0).getPosition() + this.length - car.getPosition() - car.getCarLength();
        }
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

