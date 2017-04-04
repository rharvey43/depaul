/*
 * Decompiled with CFR 0_118.
 */
package project.util;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import project.util.Animator;
import project.util.SwingAnimatorPainter;

public class SwingAnimator
implements Animator {
    private int _delay;
    private JFrame _frame;
    private ContentPane _content;
    private boolean _disposed = false;

    public SwingAnimator(final SwingAnimatorPainter painter, final String name, final int width, final int height, int delay) {
        this._delay = delay;
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                SwingAnimator.this._content = new ContentPane(painter, width, height);
                SwingAnimator.this._frame = new JFrame();
                SwingAnimator.this._frame.setTitle(name);
                SwingAnimator.this._frame.setDefaultCloseOperation(3);
                SwingAnimator.this._frame.setContentPane(SwingAnimator.this._content);
                SwingAnimator.this._frame.pack();
                SwingAnimator.this._frame.setVisible(true);
            }
        });
    }

    @Override
    public void dispose() {
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                SwingAnimator.this._frame.dispose();
                SwingAnimator.this._disposed = true;
            }
        });
    }

    @Override
    public void update(Observable model, Object ignored) {
        if (this._disposed) {
            throw new IllegalStateException();
        }
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                SwingAnimator.this._content.repaint();
            }
        });
        try {
            Thread.currentThread();
            Thread.sleep(this._delay);
        }
        catch (InterruptedException e) {
            // empty catch block
        }
    }

    private static class ContentPane
    extends JPanel {
        private static final long serialVersionUID = 2008;
        private int _width;
        private int _height;
        private SwingAnimatorPainter _painter;

        ContentPane(SwingAnimatorPainter painter, int width, int height) {
            this._painter = painter;
            this._width = width;
            this._height = height;
            this.setPreferredSize(new Dimension(width, height));
            this.setDoubleBuffered(true);
            this.setOpaque(true);
            this.setBackground(Color.WHITE);
        }

        void setPainter(SwingAnimatorPainter painter) {
            this._painter = painter;
        }

        @Override
        public void paint(Graphics g) {
            if (this._painter != null) {
                g.clearRect(0, 0, this._width, this._height);
                this._painter.paint(g);
            }
        }
    }

}

