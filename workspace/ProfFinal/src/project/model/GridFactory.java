/*
 * Decompiled with CFR 0_118.
 */
package project.model;

import java.util.ArrayList;
import project.model.Agent;
import project.model.Alternating;
import project.model.AnimatorBuilder;
import project.model.Grid;
import project.model.Intersection;
import project.model.Model;
import project.model.Simple;

public class GridFactory {
    private GridFactory() {
    }

    public static Grid newInstance(boolean alternating, Model model, ArrayList<Agent> _agents, Intersection[][] intersection, AnimatorBuilder builder) {
        if (alternating) {
            return new Alternating(model, _agents, intersection, builder);
        }
        return new Simple(model, _agents, intersection, builder);
    }
}

