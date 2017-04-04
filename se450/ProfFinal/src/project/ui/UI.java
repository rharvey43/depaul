/*
 * Decompiled with CFR 0_118.
 */
package project.ui;

import project.ui.UIForm;
import project.ui.UIMenu;

public interface UI {
    public void processMenu(UIMenu var1);

    public String[] processForm(UIForm var1);

    public void displayMessage(String var1);

    public void displayError(String var1);
}

