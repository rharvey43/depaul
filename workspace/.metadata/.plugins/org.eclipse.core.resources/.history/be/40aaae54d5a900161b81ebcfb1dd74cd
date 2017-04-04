/*
 * Decompiled with CFR 0_118.
 */
package project.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import project.model.Agent;
import project.model.AnimatorBuilder;
import project.model.GridFactory;
import project.model.Intersection;
import project.model.Light;
import project.model.MP;
import project.model.NullAnimatorBuilder;
import project.util.Animator;

public class Model
extends Observable {
    private List<Agent> _agents;
    private Animator _animator;
    private boolean _disposed;
    private double _time;

    public Model(AnimatorBuilder builder) {
        if (builder == null) {
            builder = new NullAnimatorBuilder();
        }
        this._agents = new ArrayList<Agent>();
        this.setup(builder);
        this._animator = builder.getAnimator();
        super.addObserver(this._animator);
    }

    public void run(int duration) {
        if (this._disposed) {
            throw new IllegalStateException();
        }
        for (int i = 0; i < duration; ++i) {
            Agent[] agents_copy;
            this._time += 1.0;
            for (Agent a : agents_copy = this._agents.toArray(new Agent[0])) {
                a.run(this._time);
            }
            super.setChanged();
            super.notifyObservers();
        }
    }

    public void dispose() {
        this._animator.dispose();
        this._disposed = true;
    }

    public List<Agent> getAgents() {
        return this._agents;
    }

    private void setup(AnimatorBuilder builder) {
        int rows = MP.getRows();
        int columns = MP.getColumns();
        Intersection[][] intersection = new Intersection[rows][columns];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                intersection[i][j] = new Intersection();
                builder.addLight(intersection[i][j].getEWLight(), i, j);
                this._agents.add(intersection[i][j]);
            }
        }
        GridFactory.newInstance(MP.isAlternating, this, (ArrayList)this._agents, intersection, builder);
    }

    public double getTime() {
        return this._time;
    }
}

