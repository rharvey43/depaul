/*
 * Decompiled with CFR 0_118.
 */
package project.ui;

import java.util.ArrayList;
import java.util.List;
import project.ui.UIMenu;
import project.ui.UIMenuAction;

public class UIMenuBuilder {
    private final List<UIMenu.Pair> _menu = new ArrayList<UIMenu.Pair>();

    public void add(String prompt, UIMenuAction action) {
        if (null == action) {
            throw new IllegalArgumentException();
        }
        this._menu.add(new UIMenu.Pair(prompt, action));
    }

    public UIMenu toUIMenu(String heading) {
        if (null == heading) {
            throw new IllegalArgumentException();
        }
        if (this._menu.size() <= 1) {
            throw new IllegalStateException();
        }
        UIMenu.Pair[] array = new UIMenu.Pair[this._menu.size()];
        for (int i = 0; i < this._menu.size(); ++i) {
            array[i] = this._menu.get(i);
        }
        return new UIMenu(heading, array);
    }
}

