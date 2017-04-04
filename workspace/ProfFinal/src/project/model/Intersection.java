/*
 * Decompiled with CFR 0_118.
 */
package project.model;

import java.util.List;
import project.model.Agent;
import project.model.Car;
import project.model.Light;
import project.model.LightColor;
import project.model.MP;

public class Intersection
implements Agent {
    private Light ewLight;
    private Light nsLight;
    private double length = Math.random() * (MP.maxIntersectionLength - MP.minIntersectionLength) + MP.minIntersectionLength;
    private double greenDurationNS = Math.random() * (MP.maxGreenLightTime - MP.minGreenLightTime) + MP.minGreenLightTime;
    private double yellowDurationNS = Math.random() * (MP.maxYellowLightTime - MP.minYellowLightTime) + MP.minYellowLightTime;
    private double greenDurationEW = Math.random() * (MP.maxGreenLightTime - MP.minGreenLightTime) + MP.minGreenLightTime;
    private double yellowDurationEW = Math.random() * (MP.maxYellowLightTime - MP.minYellowLightTime) + MP.minYellowLightTime;

    public Intersection() {
        this.ewLight = new Light(this.length);
        this.nsLight = new Light(this.length);
    }

    public Intersection(Light x) {
        this.ewLight = x;
        this.nsLight = new Light(this.length);
    }

    @Override
    public void run(double time) {
        boolean nsLightState;
        boolean ewLightState;
        double whereInCycle = time % (this.greenDurationNS + this.yellowDurationNS + this.greenDurationEW + this.yellowDurationEW);
        LightColor ewLightColor = LightColor.GREEN;
        LightColor nsLightColor = LightColor.RED;
        if (whereInCycle <= this.greenDurationEW + this.yellowDurationEW) {
            ewLightState = true;
            nsLightState = !ewLightState;
            ewLightColor = whereInCycle <= this.greenDurationEW ? LightColor.GREEN : LightColor.YELLOW;
            nsLightColor = LightColor.RED;
        } else {
            ewLightState = false;
            nsLightState = !ewLightState;
            nsLightColor = whereInCycle - (this.greenDurationEW + this.yellowDurationEW) <= this.greenDurationNS ? LightColor.GREEN : LightColor.YELLOW;
            ewLightColor = LightColor.RED;
        }
        if (!this.ewLight.getCars().isEmpty() || !this.nsLight.getCars().isEmpty()) {
            ewLightState = false;
            nsLightState = false;
        }
        this.ewLight.run(time, ewLightState, ewLightColor);
        this.nsLight.run(time, nsLightState, nsLightColor);
    }

    public Light getEWLight() {
        return this.ewLight;
    }

    public Light getNSLight() {
        return this.nsLight;
    }
}

