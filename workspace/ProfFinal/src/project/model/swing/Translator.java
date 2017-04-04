/*
 * Decompiled with CFR 0_118.
 */
package project.model.swing;

abstract class Translator {
    double _tX;
    double _tY;
    double _tWidth;
    double _tHeight;
    double _tScaleFactor;

    Translator(double tX, double tY, double tWidth, double tHeight, double tScaleFactor) {
        this._tX = tX;
        this._tY = tY;
        this._tWidth = tWidth;
        this._tHeight = tHeight;
        this._tScaleFactor = tScaleFactor;
    }

    int scale(double arg) {
        return (int)Math.ceil(arg * this._tScaleFactor);
    }

    abstract int getX(double var1, double var3, double var5, double var7);

    abstract int getY(double var1, double var3, double var5, double var7);

    abstract int getWidth(double var1, double var3);

    abstract int getHeight(double var1, double var3);
}

