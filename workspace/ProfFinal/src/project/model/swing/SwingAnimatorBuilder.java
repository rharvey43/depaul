/*
 * Decompiled with CFR 0_118.
 */
package project.model.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import project.model.AnimatorBuilder;
import project.model.Car;
import project.model.CarHandler;
import project.model.Light;
import project.model.LightColor;
import project.model.MP;
import project.model.Road;
import project.model.swing.Translator;
import project.model.swing.TranslatorEW;
import project.model.swing.TranslatorNS;
import project.model.swing.TranslatorSN;
import project.model.swing.TranslatorWE;
import project.model.swing.VP;
import project.model.swing.XGraphics;
import project.util.Animator;
import project.util.SwingAnimator;
import project.util.SwingAnimatorPainter;

public class SwingAnimatorBuilder
implements AnimatorBuilder {
    MyPainter _painter = new MyPainter();
    private static double skipInit = VP.gap;
    private static double skipRoad = VP.gap + MP.roadDrawLength;
    private static double skipCar = VP.gap + VP.elementWidth;
    private static double skipRoadCar = skipRoad + skipCar;

    @Override
    public Animator getAnimator() {
        if (this._painter == null) {
            throw new IllegalStateException();
        }
        SwingAnimator returnValue = new SwingAnimator(this._painter, "Traffic Simulator", VP.displayWidth, VP.displayHeight, VP.displayDelay);
        this._painter = null;
        return returnValue;
    }

    @Override
    public void addLight(Light d, int i, int j) {
        double x = skipInit + skipRoad + (double)j * skipRoadCar;
        double y = skipInit + skipRoad + (double)i * skipRoadCar;
        TranslatorWE t = new TranslatorWE(x, y, MP.carLength, VP.elementWidth, VP.scaleFactor);
        this._painter.addLight(d, t);
    }

    @Override
    public void addHorizontalRoad(Road l, int i, int j, boolean eastToWest) {
        double x = skipInit + (double)j * skipRoadCar;
        double y = skipInit + skipRoad + (double)i * skipRoadCar;
        Translator t = eastToWest ? new TranslatorEW(x, y, MP.roadDrawLength, VP.elementWidth, VP.scaleFactor) : new TranslatorWE(x, y, MP.roadDrawLength, VP.elementWidth, VP.scaleFactor);
        this._painter.addRoad(l, t);
    }

    @Override
    public void addVerticalRoad(Road l, int i, int j, boolean southToNorth) {
        double x = skipInit + skipRoad + (double)j * skipRoadCar;
        double y = skipInit + (double)i * skipRoadCar;
        Translator t = southToNorth ? new TranslatorSN(x, y, MP.roadDrawLength, VP.elementWidth, VP.scaleFactor) : new TranslatorNS(x, y, MP.roadDrawLength, VP.elementWidth, VP.scaleFactor);
        this._painter.addRoad(l, t);
    }

    private static class MyPainter
    implements SwingAnimatorPainter {
        private List<Element<Road>> _roadElements = new ArrayList<Element<Road>>();
        private List<Element<Light>> _lightElements = new ArrayList<Element<Light>>();

        MyPainter() {
        }

        void addLight(Light x, Translator t) {
            this._lightElements.add(new Element<Light>(x, t));
        }

        void addRoad(Road x, Translator t) {
            this._roadElements.add(new Element<Road>(x, t));
        }

        @Override
        public void paint(Graphics g) {
            for (Element<Light> e22 : this._lightElements) {
                if (((Light)e22.x).getColor() == LightColor.GREEN) {
                    g.setColor(Color.GREEN);
                } else if (((Light)e22.x).getColor() == LightColor.YELLOW) {
                    g.setColor(Color.YELLOW);
                } else {
                    g.setColor(Color.RED);
                }
                XGraphics.fillOval(g, e22.t, 0.0, 0.0, MP.carLength, VP.elementWidth);
            }
            g.setColor(Color.BLACK);
            for (Element e : this._roadElements) {
                XGraphics.fillRect(g, e.t, 0.0, 0.0, MP.roadDrawLength, VP.elementWidth);
            }
            for (Element e2 : this._roadElements) {
                for (Car d : ((Road)e2.x).getCars().toArray(new Car[0])) {
                    g.setColor(d.getColor());
                    XGraphics.fillOval(g, e2.t, d.getPosition() / (d.getOberver().getLength() / MP.roadDrawLength), 0.0, d.getCarLength() / (d.getOberver().getLength() / MP.roadDrawLength), VP.elementWidth);
                }
            }
        }

        private static class Element<T> {
            T x;
            Translator t;

            Element(T x, Translator t) {
                this.x = x;
                this.t = t;
            }
        }

    }

}

