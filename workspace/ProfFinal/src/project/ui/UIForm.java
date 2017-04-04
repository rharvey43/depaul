/*
 * Decompiled with CFR 0_118.
 */
package project.ui;

import project.ui.UIFormTest;

public final class UIForm {
    private final String _heading;
    private final Pair[] _form;

    UIForm(String heading, Pair[] menu) {
        this._heading = heading;
        this._form = menu;
    }

    public int size() {
        return this._form.length;
    }

    public String getHeading() {
        return this._heading;
    }

    public String getPrompt(int i) {
        return this._form[i].prompt;
    }

    public boolean checkInput(int i, String input) {
        if (null == this._form[i]) {
            return true;
        }
        return this._form[i].test.run(input);
    }

    static final class Pair {
        final String prompt;
        final UIFormTest test;

        Pair(String thePrompt, UIFormTest theTest) {
            this.prompt = thePrompt;
            this.test = theTest;
        }
    }

}

