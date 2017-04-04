/*
 * Decompiled with CFR 0_118.
 */
package model;

import java.util.List;
import model.Car;
import model.CarHandlerList;

public interface CarHandler 
{
    public void accept(Car var1);

    public void remove(Car var1);

    public List<Car> getCars();

    public int getYPos();

    public int getXPos();

    public boolean getDirect();

    public int vertCompareTo(CarHandler var1);

    public int horCompareTo(CarHandler var1);

    public void addObserver(CarHandlerList var1);

    public void removeObserver();

    public CarHandlerList getObservingCarHandlerList();

    public double getDistancetoNextObstacle(Car var1);

    public boolean getState();

    public double getLength();
}

