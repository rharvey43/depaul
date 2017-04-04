/*
 * Decompiled with CFR 0_118.
 */
package project.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Scanner;
import project.ui.UI;
import project.ui.UIError;
import project.ui.UIForm;
import project.ui.UIMenu;

public final class TextUI
implements UI {
    final BufferedReader _in = new BufferedReader(new InputStreamReader(System.in));
    final PrintStream _out = System.out;

    @Override
    public void displayMessage(String message) {
        this._out.println(message);
    }

    @Override
    public void displayError(String message) {
        this._out.println(message);
    }

    private String getResponse() {
        String result;
        try {
            result = this._in.readLine();
        }
        catch (IOException e) {
            throw new UIError();
        }
        if (result == null) {
            throw new UIError();
        }
        return result;
    }

    @Override
    public void processMenu(UIMenu menu) {
        int selection;
        this._out.println(menu.getHeading());
        this._out.println("Enter choice by number:");
        for (int i = 1; i < menu.size(); ++i) {
            this._out.println("  " + i + ". " + menu.getPrompt(i));
        }
        String response = this.getResponse();
        try {
            selection = Integer.parseInt(response, 10);
            if (selection < 0 || selection >= menu.size()) {
                selection = 0;
            }
        }
        catch (NumberFormatException e) {
            selection = 0;
        }
        menu.runAction(selection);
    }

    @Override
    public String[] processForm(UIForm form) {
        Scanner scan = new Scanner(System.in);
        String[] result = new String[form.size()];
        for (int x = 0; x < form.size(); ++x) {
            System.out.print("Enter " + form.getPrompt(x) + ": ");
            String response = scan.next();
            if (response == null) {
                response = "0";
            }
            result[x] = new String(response);
        }
        return result;
    }
}

