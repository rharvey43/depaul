/*
 * Decompiled with CFR 0_118.
 */
package project.model.swing;

import project.model.swing.Translator;

class TranslatorNS
extends Translator {
    TranslatorNS(double tX, double tY, double tWidth, double tHeight, double tScaleFactor) {
        super(tX, tY, tWidth, tHeight, tScaleFactor);
    }

    @Override
    int getX(double x, double y, double width, double height) {
        return this.scale(this._tX + y);
    }

    @Override
    int getY(double x, double y, double width, double height) {
        return this.scale(this._tY + x);
    }

    @Override
    int getWidth(double width, double height) {
        return this.scale(height);
    }

    @Override
    int getHeight(double width, double height) {
        return this.scale(width);
    }
}

