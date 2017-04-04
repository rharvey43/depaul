/*
 * Decompiled with CFR 0_118.
 */
package project.model;

import java.util.ArrayList;
import java.util.List;
import project.model.Agent;
import project.model.Car;
import project.model.CarHandler;
import project.model.CarHandlerList;
import project.model.LightColor;
import project.model.MP;
import project.model.Model;
import project.model.Queue;

public class Light
implements CarHandler {
    private LightColor _lightColor;
    private double length;
    private int xposition;
    private int yposition;
    private boolean trafficDirection;
    private CarHandlerList observer;
    private boolean _state;
    private Queue<Car> carQueue = new Queue();
    private List<Car> _cars = new ArrayList<Car>();

    Light(double l) {
        this.length = l;
    }

    public void run(double time, boolean ewLightState, LightColor ewLightColor) {
        if (!this.carQueue.isEmpty()) {
            this.moveWaitingCarsAcross();
        }
        this._state = ewLightState;
        this._lightColor = ewLightColor;
    }

    @Override
    public boolean getState() {
        return this._state;
    }

    @Override
    public void accept(Car d) {
        Model model = this.observer.getObserver();
        d.addObserver(this);
        this._cars.add(d);
        model.getAgents().remove(d);
        this.carQueue.enqueue(d);
        this.moveWaitingCarsAcross();
    }

    private void moveWaitingCarsAcross() {
        Model model = this.observer.getObserver();
        List<Car> nextRoadTraffic = this.observer.getNext(this).getCars();
        if (!nextRoadTraffic.isEmpty()) {
            Car nextCar = nextRoadTraffic.get(nextRoadTraffic.size() - 1);
            double distance = nextCar.getPosition() - nextCar.getCarLength();
            if (distance > MP.maxCarLength) {
                this._cars.remove(this.carQueue.peek());
                model.getAgents().add(this.carQueue.peek());
                this.observer.getNext(this).accept(this.carQueue.dequeue());
            }
        } else {
            this._cars.remove(this.carQueue.peek());
            model.getAgents().add(this.carQueue.peek());
            this.observer.getNext(this).accept(this.carQueue.dequeue());
        }
    }

    public LightColor getColor() {
        return this._lightColor;
    }

    @Override
    public void addObserver(CarHandlerList carhandlerlist) {
        this.observer = carhandlerlist;
    }

    @Override
    public List<Car> getCars() {
        return this._cars;
    }

    @Override
    public boolean getDirect() {
        return this.trafficDirection;
    }

    @Override
    public double getDistancetoNextObstacle(Car car) {
        if (this._cars.size() > 1 && this._cars.indexOf(car) != 0) {
            Car comparingCar = this._cars.get(this._cars.indexOf(car) - 1);
            return comparingCar.getPosition() - car.getPosition() - car.getCarLength();
        }
        if (!this.observer.getNext(this).getCars().isEmpty()) {
            return this.observer.getNext(this).getCars().get(0).getPosition() + MP.roadLength - car.getPosition() - car.getCarLength();
        }
        return car.getBreakDistance() + 0.1;
    }

    @Override
    public CarHandlerList getObservingCarHandlerList() {
        return this.observer;
    }

    @Override
    public int getXPos() {
        return this.xposition;
    }

    @Override
    public int getYPos() {
        return this.yposition;
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
    public void remove(Car d) {
        if (d == null) {
            throw new IllegalArgumentException();
        }
        this._cars.remove(d);
        d.removeObserver();
    }

    @Override
    public void removeObserver() {
        this.observer = null;
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
    public double getLength() {
        return this.length;
    }
}

