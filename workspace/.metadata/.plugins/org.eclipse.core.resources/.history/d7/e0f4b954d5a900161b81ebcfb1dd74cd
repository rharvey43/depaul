/*
 * Decompiled with CFR 0_118.
 */
package project.model;

import java.awt.Color;
import project.model.Agent;
import project.model.CarHandler;
import project.model.CarHandlerList;
import project.model.MP;

public class Car
implements Agent {
    private String state = "";
    private CarHandler observer;
    private double _position = 0.0;
    private double _carLength = (double)((int)(MP.maxCarLength * Math.random())) + MP.minCarLength;
    private double _maxVelocity;
    private double _velocity;
    private double _breakDistance;
    private double _stopDistance;
    private Color _color;

    Car() {
        this._velocity = this._maxVelocity = Math.random() * (MP.maxVelocity - MP.minVelocity) + MP.minVelocity;
        this._breakDistance = Math.random() * (MP.maxBreakDistance - MP.minBreakDistance) + MP.minBreakDistance;
        this._stopDistance = Math.random() * (MP.maxStopDistance - MP.minStopDistance) + MP.minStopDistance;
        this._color = new Color((int)Math.ceil(Math.random() * 255.0), (int)Math.ceil(Math.random() * 255.0), (int)Math.ceil(Math.random() * 255.0));
        this._maxVelocity = Math.random() * (MP.maxVelocity - MP.minVelocity) + MP.minVelocity;
    }

    public double getPosition() {
        return this._position;
    }

    public double getNextPosition() {
        return this._position += this._velocity;
    }

    public double getVelocity() {
        return this._velocity;
    }

    public Color getColor() {
        return this._color;
    }

    @Override
    public void run(double time) {
        this.checkTailGate();
        this.checkPosOnRoad();
        this._position += this._velocity;
    }

    public void checkTailGate() {
        double distanceBetween = this.observer.getDistancetoNextObstacle(this);
        if (distanceBetween <= this._breakDistance) {
            if (distanceBetween <= this._stopDistance) {
                this._velocity *= MP.stopFactor;
            } else {
                this._velocity *= MP.breakFactor;
                if (this._velocity >= distanceBetween) {
                    this._velocity = MP.minVelocity;
                }
            }
        } else {
            this._velocity = this._velocity == 0.0 ? this._maxVelocity / 2.0 : this._maxVelocity;
        }
    }

    public void checkPosOnRoad() {
        if (this.getPosition() > this.observer.getLength() - this._carLength) {
            CarHandler nextCarHandler = this.observer.getObservingCarHandlerList().getNext(this.observer);
            if (nextCarHandler != this.observer) {
                if (nextCarHandler.getState()) {
                    this.observer.remove(this);
                    nextCarHandler.accept(this);
                    this._position = 0.0;
                }
            } else {
                this._velocity = 0.0;
                this.observer.remove(this);
            }
        }
    }

    public String getState() {
        return this.state;
    }

    public void addObserver(CarHandler carhandler) {
        this.observer = carhandler;
    }

    public CarHandler getOberver() {
        return this.observer;
    }

    public void removeObserver() {
        this.observer = null;
    }

    public double getCarLength() {
        return this._carLength;
    }

    public double getBreakDistance() {
        return this._breakDistance;
    }

    public void setPosition(double pos) {
        this._position = pos;
    }

    public void setSpeed(double speed) {
        this._maxVelocity = speed;
    }
}

