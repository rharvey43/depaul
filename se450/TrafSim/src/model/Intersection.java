/*
 * Decompiled with CFR 0_118.
 */
package model;



import managers.ParameterManager;
import model.Agent;
import model.Light;
import model.LightColor;

public class Intersection implements Agent 
{
    private Light ewLight;
    private Light nsLight;
    private double length = Math.random() * (ParameterManager.getParameterManager().getIntersectionLength().getMax() - ParameterManager.getParameterManager().getIntersectionLength().getMin()) + ParameterManager.getParameterManager().getIntersectionLength().getMin();
    private double greenDurationNS = Math.random() * (ParameterManager.getParameterManager().getTrafficLightGreenTime().getMax() - ParameterManager.getParameterManager().getTrafficLightGreenTime().getMin()) + ParameterManager.getParameterManager().getTrafficLightGreenTime().getMin();
    private double yellowDurationNS = Math.random() * (ParameterManager.getParameterManager().getTrafficLightYellowTime().getMax() - ParameterManager.getParameterManager().getTrafficLightYellowTime().getMin()) + ParameterManager.getParameterManager().getTrafficLightYellowTime().getMin();
    private double greenDurationEW = Math.random() * (ParameterManager.getParameterManager().getTrafficLightGreenTime().getMax() - ParameterManager.getParameterManager().getTrafficLightGreenTime().getMin()) + ParameterManager.getParameterManager().getTrafficLightGreenTime().getMin();
    private double yellowDurationEW = Math.random() * (ParameterManager.getParameterManager().getTrafficLightYellowTime().getMax() - ParameterManager.getParameterManager().getTrafficLightYellowTime().getMin()) + ParameterManager.getParameterManager().getTrafficLightYellowTime().getMin();

    public Intersection() 
    {
        this.ewLight = new Light(this.length);
        this.nsLight = new Light(this.length);
    }

    public Intersection(Light l) 
    {
        this.ewLight = l;
        this.nsLight = new Light(this.length);
    }

    @Override
    public void run(double time) 
    {
        boolean nsLightState;
        boolean ewLightState;
        double whereInCycle = time % (greenDurationNS + yellowDurationNS + greenDurationEW + yellowDurationEW);
        LightColor ewLightColor = LightColor.GREEN;
        LightColor nsLightColor = LightColor.RED;
        if (whereInCycle <= greenDurationEW + yellowDurationEW) 
        {
            ewLightState = true;
            nsLightState = !ewLightState;
            ewLightColor = whereInCycle <= greenDurationEW ? LightColor.GREEN : LightColor.YELLOW;
            nsLightColor = LightColor.RED;
        } 
        
        else 
        {
            ewLightState = false;
            nsLightState = !ewLightState;
            nsLightColor = whereInCycle - (greenDurationEW + yellowDurationEW) <= greenDurationNS ? LightColor.GREEN : LightColor.YELLOW;
            ewLightColor = LightColor.RED;
        }
        if (!ewLight.getCars().isEmpty() || !nsLight.getCars().isEmpty()) {
            ewLightState = false;
            nsLightState = false;
        }
        ewLight.run(time, ewLightState, ewLightColor);
        nsLight.run(time, nsLightState, nsLightColor);
    }

    public Light getEWLight() 
    {
        return ewLight;
    }

    public Light getNSLight() 
    {
        return nsLight;
    }
}

