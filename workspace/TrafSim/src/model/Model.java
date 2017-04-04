/*
 * Decompiled with CFR 0_118.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import managers.ParameterManager;
import model.Agent;
import model.AnimatorBuilder;
import model.GridFactory;
import model.Intersection;
import model.NullAnimatorBuilder;
import util.Animator;

public class Model extends Observable 
{
    private List<Agent> agents;
    private Animator animator;
    private boolean disposed;
    private double time;

    public Model(AnimatorBuilder builder) 
    {
        if (builder == null) 
        {
            builder = new NullAnimatorBuilder();
        }
        agents = new ArrayList<Agent>();
        this.setup(builder);
        animator = builder.getAnimator();
        super.addObserver(animator);
    }

    public void run(int duration) 
    {
        if (disposed) 
        {
            throw new IllegalStateException();
        }
        for (int i = 0; i < duration; ++i) 
        {
            //Agent[] agents_copy;
            time += 1.0;
            for (Agent a : agents.toArray(new Agent[0])) 
            {
                a.run(time);
            }
            super.setChanged();
            super.notifyObservers();
        }
    }

    public void dispose() 
    {
        animator.dispose();
        disposed = true;
    }

    public List<Agent> getAgents() 
    {
        return agents;
    }

    private void setup(AnimatorBuilder builder) 
    {
        int rows = ParameterManager.getParameterManager().getGridSize().getRow();
        int columns = ParameterManager.getParameterManager().getGridSize().getColumn();
        Intersection[][] intersection = new Intersection[rows][columns];
        for (int i = 0; i < rows; ++i) 
        {
            for (int j = 0; j < columns; ++j) 
            {
                intersection[i][j] = new Intersection();
                builder.addLight(intersection[i][j].getEWLight(), i, j);
                agents.add(intersection[i][j]);
            }
        }
        GridFactory.newInstance(ParameterManager.getParameterManager().getTrafficPattern(), this, (ArrayList<Agent>)agents, intersection, builder);
    }

    public double getTime() 
    {
        return time;
    }
}

