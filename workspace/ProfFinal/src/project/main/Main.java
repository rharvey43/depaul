/*
 * Decompiled with CFR 0_118.
 */
package project.main;

import project.main.Control;
import project.ui.PopupUI;
import project.ui.UI;

public class Main {
    private Main() {
    }

    public static void main(String[] args) {
        PopupUI ui = new PopupUI();
        Control control = new Control(ui);
        control.run();
    }
}

