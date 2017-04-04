/*
 * Decompiled with CFR 0_118.
 */
package project.model;

import project.model.Light;
import project.model.Road;
import project.util.Animator;

public interface AnimatorBuilder {
    public Animator getAnimator();

    public void addLight(Light var1, int var2, int var3);

    public void addHorizontalRoad(Road var1, int var2, int var3, boolean var4);

    public void addVerticalRoad(Road var1, int var2, int var3, boolean var4);
}

