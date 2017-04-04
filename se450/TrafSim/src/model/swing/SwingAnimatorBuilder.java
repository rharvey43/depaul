/*
 * Decompiled with CFR 0_118.
 */
package model.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import managers.ParameterManager;
import model.AnimatorBuilder;
import model.Car;
import model.Light;
import model.LightColor;
import model.Road;
import model.swing.Translator;
import model.swing.TranslatorEW;
import model.swing.TranslatorNS;
import model.swing.TranslatorSN;
import model.swing.TranslatorWE;
import model.swing.VP;
import model.swing.XGraphics;
import util.Animator;
import util.SwingAnimator;
import util.SwingAnimatorPainter;

public class SwingAnimatorBuilder implements AnimatorBuilder 
{
    MyPainter painter = new MyPainter();
    private static double skipInit = VP.gap;
    private static double skipRoad = VP.gap + ParameterManager.getParameterManager().getRoadSegmentLength().getMin();
    private static double skipCar = VP.gap + VP.elementWidth;
    private static double skipRoadCar = skipRoad + skipCar;
    private static double roadDrawLength = ParameterManager.getParameterManager().getRoadSegmentLength().getMin();
    private static double generatedCarLength = ParameterManager.getParameterManager().getGeneratedCarLength();
    
    @Override
    public Animator getAnimator() 
    {
        if (painter == null) 
        {
            throw new IllegalStateException();
        }
        SwingAnimator returnValue = new SwingAnimator(painter, "Traffic Simulator", VP.displayWidth, VP.displayHeight, VP.displayDelay);
        painter = null;
        return returnValue;
    }

    @Override
    public void addLight(Light d, int i, int j) 
    {
        double x = skipInit + skipRoad + (double)j * skipRoadCar;
        double y = skipInit + skipRoad + (double)i * skipRoadCar;
        TranslatorWE t = new TranslatorWE(x, y, generatedCarLength, VP.elementWidth, VP.scaleFactor);
        painter.addLight(d, t);
    }

    @Override
    public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) 
    {
        double x = skipInit + (double)j * skipRoadCar;
        double y = skipInit + skipRoad + (double)i * skipRoadCar;
        Translator t = eastToWest ? new TranslatorEW(x, y, roadDrawLength, VP.elementWidth, VP.scaleFactor) : new TranslatorWE(x, y, roadDrawLength, VP.elementWidth, VP.scaleFactor);
        painter.addRoad(l, t);
    }

    @Override
    public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) 
    {
        double x = skipInit + skipRoad + (double)j * skipRoadCar;
        double y = skipInit + (double)i * skipRoadCar;
        Translator t = southToNorth ? new TranslatorSN(x, y, roadDrawLength, VP.elementWidth, VP.scaleFactor) : new TranslatorNS(x, y, roadDrawLength, VP.elementWidth, VP.scaleFactor);
        painter.addRoad(l, t);
    }

    private static class MyPainter
    implements SwingAnimatorPainter 
    {
        private List<Element<Road>> roadElements = new ArrayList<Element<Road>>();
        private List<Element<Light>> lightElements = new ArrayList<Element<Light>>();

        MyPainter() 
        {
        }

        void addLight(Light x, Translator t) 
        {
            lightElements.add(new Element<Light>(x, t));
        }

        void addRoad(Road x, Translator t) 
        {
            roadElements.add(new Element<Road>(x, t));
        }

        @Override
        public void paint(Graphics g) 
        {
            for (Element<Light> e22 : lightElements) 
            {
                if (((Light)e22.x).getColor() == LightColor.GREEN) 
                {
                    g.setColor(Color.GREEN);
                } else if (((Light)e22.x).getColor() == LightColor.YELLOW) 
                {
                    g.setColor(Color.YELLOW);
                } 
                else 
                {
                    g.setColor(Color.RED);
                }
                XGraphics.fillOval(g, e22.t, 0.0, 0.0, generatedCarLength, VP.elementWidth);
            }
            g.setColor(Color.BLACK);
            for (Element e : roadElements) 
            {
                XGraphics.fillRect(g, e.t, 0.0, 0.0, roadDrawLength, VP.elementWidth);
            }
            for (Element e2 : roadElements) 
            {
                for (Car d : ((Road)e2.x).getCars().toArray(new Car[0])) 
                {
                    g.setColor(d.getColor());
                    XGraphics.fillOval(g, e2.t, d.getPosition() / (d.getOberver().getLength() / roadDrawLength), 0.0, d.getCarLength() / (d.getOberver().getLength() /roadDrawLength), VP.elementWidth);
                }
            }
        }

        private static class Element<T> 
        {
            T x;
            Translator t;

            Element(T x, Translator t) 
            {
                this.x = x;
                this.t = t;
            }
        }

    }

}

