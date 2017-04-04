/*
 * Decompiled with CFR 0_118.
 */
package project.main;

import project.model.AnimatorBuilder;
import project.model.MP;
import project.model.Model;
import project.model.swing.SwingAnimatorBuilder;
import project.ui.UI;
import project.ui.UIError;
import project.ui.UIForm;
import project.ui.UIFormBuilder;
import project.ui.UIFormTest;
import project.ui.UIMenu;
import project.ui.UIMenuAction;
import project.ui.UIMenuBuilder;

class Control {
    private static final int EXITED = 0;
    private static final int EXIT = 1;
    private static final int SETTINGS = 3;
    private static final int RUN = 2;
    private UIMenu[] _menus;
    private int _state;
    private UIForm _setSingleDouble;
    private UIForm _setDoubleDouble;
    private UIForm _setDoubleInt;
    private UIForm _setPattern;
    private UIFormTest _doubleTest;
    private UIFormTest _numberTest;
    private UI _ui;

    Control(UI ui) {
        this._ui = ui;
        this._menus = new UIMenu[4];
        this._state = 2;
        this.addSTART(2);
        this.addSETTINGS(3);
        this.addEXIT(1);
        this._numberTest = new UIFormTest(){

            @Override
            public boolean run(String input) {
                try {
                    Integer.parseInt(input);
                    return true;
                }
                catch (NumberFormatException e) {
                    return false;
                }
            }
        };
        this._doubleTest = new UIFormTest(){

            @Override
            public boolean run(String input) {
                try {
                    Double.parseDouble(input);
                    return true;
                }
                catch (NumberFormatException e) {
                    return false;
                }
            }
        };
        UIFormBuilder singleDouble = new UIFormBuilder();
        singleDouble.add("value:", this._doubleTest);
        this._setSingleDouble = singleDouble.toUIForm("Enter value: ");
        UIFormBuilder singleDouble2 = new UIFormBuilder();
        singleDouble2.add("value (default value is " + MP.getRuntime() + "):", this._doubleTest);
        this._setSingleDouble = singleDouble2.toUIForm("Enter value: ");
        UIFormBuilder doubleInt = new UIFormBuilder();
        doubleInt.add("numbers of rows (default value is " + MP.getRows() + "):", this._numberTest);
        doubleInt.add("numbers of columns (default value is " + MP.getColumns() + "):", this._numberTest);
        this._setDoubleInt = doubleInt.toUIForm("Enter value: ");
        UIFormBuilder setPattern = new UIFormBuilder();
        setPattern.add("1 for simple pattern and 2 for alternating: ", this._numberTest);
        this._setPattern = setPattern.toUIForm("Enter value: ");
        UIFormBuilder setDoubleDouble = new UIFormBuilder();
        setDoubleDouble.add("a minimum: ", this._numberTest);
        setDoubleDouble.add("a maximum: ", this._numberTest);
        this._setDoubleDouble = setDoubleDouble.toUIForm(": ");
    }

    void run() {
        try {
            while (this._state != 0) {
                this._ui.processMenu(this._menus[this._state]);
            }
        }
        catch (UIError e) {
            this._ui.displayError("UI closed");
        }
    }

    private void addSTART(int stateNum) {
        UIMenuBuilder m = new UIMenuBuilder();
        m.add("Default", new UIMenuAction(){

            @Override
            public void run() {
            }
        });
        m.add("Run simulation", new UIMenuAction(){

            @Override
            public void run() {
                Control.this._state = 2;
                Model m = new Model(new SwingAnimatorBuilder());
                m.run(MP.simulationRuntime);
                m.dispose();
            }
        });
        m.add("Change simulation parameters", new UIMenuAction(){

            @Override
            public void run() {
                Control.this._state = 3;
            }
        });
        m.add("Exit", new UIMenuAction(){

            @Override
            public void run() {
                Control.this._state = 1;
            }
        });
        this._menus[stateNum] = m.toUIMenu("Simulation City");
    }

    private void addSETTINGS(int stateNum) {
        UIMenuBuilder m = new UIMenuBuilder();
        m.add("Default", new UIMenuAction(){

            @Override
            public void run() {
                Control.this._ui.displayError("doh!");
            }
        });
        m.add("Show current values", new UIMenuAction(){

            @Override
            public void run() {
                StringBuilder b = MP.returnCurrentValues();
                Control.this._ui.displayMessage(b.toString());
            }
        });
        m.add("Simulation time step", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setSingleDouble);
                MP.setTimeStep(Double.parseDouble(result1[0]));
            }
        });
        m.add("Simulation runtime", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setSingleDouble);
                MP.setRuntime(Double.parseDouble(result1[0]));
            }
        });
        m.add("Grid size", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setDoubleInt);
                MP.setGridSize(Integer.parseInt(result1[0]), Integer.parseInt(result1[1]));
            }
        });
        m.add("Set traffic pattern", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setPattern);
                if (Integer.parseInt(result1[0]) == 1) {
                    MP.setPattern(false);
                } else {
                    MP.setPattern(true);
                }
            }
        });
        m.add("Set car entry rate", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
                MP.setCarEntryRate(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
            }
        });
        m.add("Set road lengths", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
                MP.setRoadLengths(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
            }
        });
        m.add("Set intersection lengths", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
                MP.setIntersectionLength(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
            }
        });
        m.add("Set car length", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
                MP.setCarLength(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
            }
        });
        m.add("Set max car velocity", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setSingleDouble);
                MP.setCarMaxVel(Double.parseDouble(result1[0]));
            }
        });
        m.add("Set car stop distance", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
                MP.setCarStopDist(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
            }
        });
        m.add("Set car break distance", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
                MP.setCarBreakDist(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
            }
        });
        m.add("Set traffic light green times", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
                MP.setGreenTime(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
            }
        });
        m.add("Set traffic light yellow times", new UIMenuAction(){

            @Override
            public void run() {
                String[] result1 = Control.this._ui.processForm(Control.this._setDoubleDouble);
                MP.setYellowTime(Double.parseDouble(result1[0]), Double.parseDouble(result1[1]));
            }
        });
        m.add("Reset simulation and return to main menu", new UIMenuAction(){

            @Override
            public void run() {
                MP.reset();
                Control.this._state = 2;
            }
        });
        m.add("Return to main menu", new UIMenuAction(){

            @Override
            public void run() {
                Control.this._state = 2;
            }
        });
        this._menus[stateNum] = m.toUIMenu("100 miles-per-hour switchin' lanes like whoa");
    }

    private void addEXIT(int stateNum) {
        UIMenuBuilder m = new UIMenuBuilder();
        m.add("Default", new UIMenuAction(){

            @Override
            public void run() {
            }
        });
        m.add("Yes", new UIMenuAction(){

            @Override
            public void run() {
                Control.this._state = 0;
            }
        });
        m.add("No", new UIMenuAction(){

            @Override
            public void run() {
                Control.this._state = 2;
            }
        });
        this._menus[stateNum] = m.toUIMenu("Are you sure you want to exit?");
    }

}

