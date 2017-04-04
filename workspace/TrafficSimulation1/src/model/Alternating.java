/*
 * Decompiled with CFR 0_118.
 */
package model;

import java.util.ArrayList;

import managers.ParameterManager;
import model.Agent;
import model.AnimatorBuilder;
import model.CarHandler;
import model.CarHandlerList;
import model.Grid;
import model.Intersection;
import model.Light;
import model.MP;
import model.Model;
import model.Road;
import model.Sink;
import model.Source;

class Alternating
implements Grid {
    Alternating(Model model, ArrayList<Agent> _agents, Intersection[][] intersection, AnimatorBuilder builder) {
        int rows = ParameterManager.getParameterManager().getGridSize().getRow();
        int columns = ParameterManager.getParameterManager().getGridSize().getColumn();
        boolean westToEast = false;
        boolean isStreet = true;
        for (int i = 0; i < rows; ++i) {
            CarHandlerList street = new CarHandlerList(isStreet, model, westToEast);
            Source source = new Source(i, -1, westToEast);
            street.insert(source);
            _agents.add(source);
            for (int j = 0; j < columns; ++j) {
                Road s = new Road(i, j, westToEast);
                street.insert(s);
                if (!westToEast) {
                    builder.addHorizontalRoad(s, s.getXPos(), s.getYPos(), westToEast);
                    street.insert(intersection[i][j].getEWLight());
                    continue;
                }
                builder.addHorizontalRoad(s, s.getXPos(), columns - s.getYPos(), westToEast);
                street.insert(intersection[i][columns - 1 - j].getEWLight());
            }
            Road s = new Road(i, columns, westToEast);
            street.insert(s);
            if (!westToEast) {
                builder.addHorizontalRoad(s, s.getXPos(), s.getYPos(), westToEast);
            } else {
                builder.addHorizontalRoad(s, s.getXPos(), columns - s.getYPos(), westToEast);
            }
            street.insert(new Sink(i, columns, westToEast));
            westToEast = !westToEast;
        }
        boolean southToNorth = false;
        isStreet = false;
        for (int j = 0; j < columns; ++j) {
            CarHandlerList avenue = new CarHandlerList(isStreet, model, southToNorth);
            Source source = new Source(-1, j, southToNorth);
            avenue.insert(source);
            _agents.add(source);
            for (int i2 = 0; i2 < rows; ++i2) {
                Road a = new Road(i2, j, southToNorth);
                avenue.insert(a);
                if (!southToNorth) {
                    builder.addVerticalRoad(a, a.getXPos(), a.getYPos(), southToNorth);
                    avenue.insert(intersection[i2][j].getNSLight());
                    continue;
                }
                builder.addVerticalRoad(a, rows - a.getXPos(), a.getYPos(), southToNorth);
                avenue.insert(intersection[rows - 1 - i2][j].getNSLight());
            }
            Road l = new Road(rows, j, southToNorth);
            avenue.insert(l);
            if (!southToNorth) {
                builder.addVerticalRoad(l, l.getXPos(), l.getYPos(), southToNorth);
            } else {
                builder.addVerticalRoad(l, rows - l.getXPos(), l.getYPos(), southToNorth);
            }
            avenue.insert(new Sink(rows, j, southToNorth));
            southToNorth = !southToNorth;
        }
    }
}

