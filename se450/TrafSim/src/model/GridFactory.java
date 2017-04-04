/*
 * Decompiled with CFR 0_118.
 */
package model;

import java.util.ArrayList;
import model.Agent;
import model.Alternating;
import model.AnimatorBuilder;
import model.Grid;
import model.Intersection;
import model.Model;
import model.Simple;

public class GridFactory 
{
    private GridFactory() 
    {
    }

    public static Grid newInstance(TrafficPattern alternating, Model model, ArrayList<Agent> agents, Intersection[][] intersection, AnimatorBuilder builder) 
    {
        if (alternating.equals(TrafficPattern.ALTERNATING)) 
        {
            return new Alternating(model, agents, intersection, builder);
        }
        return new Simple(model, agents, intersection, builder);
    }
}

