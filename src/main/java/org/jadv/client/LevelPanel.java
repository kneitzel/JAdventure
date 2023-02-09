package org.jadv.client;

import org.jadv.model.level.Level;
import org.jadv.model.objects.GameObject;

import javax.swing.*;
import java.awt.*;

public class LevelPanel extends JPanel {

    private final ApplicationModel model;

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public LevelPanel(ApplicationModel model) {
        this.model = model;
    }

    /**
     * Calls the UI delegate's paint method, if the UI delegate
     * is non-<code>null</code>.  We pass the delegate a copy of the
     * <code>Graphics</code> object to protect the rest of the
     * paint code from irrevocable changes
     * (for example, <code>Graphics.translate</code>).
     * <p>
     * If you override this in a subclass you should not make permanent
     * changes to the passed in <code>Graphics</code>. For example, you
     * should not alter the clip <code>Rectangle</code> or modify the
     * transform. If you need to do these operations you may find it
     * easier to create a new <code>Graphics</code> from the passed in
     * <code>Graphics</code> and manipulate it. Further, if you do not
     * invoke super's implementation you must honor the opaque property, that is
     * if this component is opaque, you must completely fill in the background
     * in an opaque color. If you do not honor the opaque property you
     * will likely see visual artifacts.
     * <p>
     * The passed in <code>Graphics</code> object might
     * have a transform other than the identify transform
     * installed on it.  In this case, you might get
     * unexpected results if you cumulatively apply
     * another transform.
     *
     * @param g the <code>Graphics</code> object to protect
     * @see #paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int realHeight = model.getLevel().getHeight() * model.getScaleFactor();
        int realWidth = model.getLevel().getWidth() * model.getScaleFactor();

        // To move: Calculate x/y
        int maxY = realHeight - getHeight();
        int maxX = realWidth - getWidth();
        if (maxY < model.getY() && 0 <= maxY) model.setY(maxY);
        if (maxX < model.getX() && 0 <= maxX) model.setX(maxX);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.BLACK);
        g2d.clearRect(0,0, getWidth(), getHeight());


        Level level = model.getLevel();

        drawImage(g2d, level.getGraphicResource(), level.getWidth()/2, level.getHeight()/2, level.getWidth(), level.getHeight(), model.getScaleFactor(), model.getX(), model.getY());

        for (GameObject object : level.getChildren()) {
            drawImage(g2d, object.getGraphicResource(), object.getPosition().getX(), object.getPosition().getY(), object.getSize().getWidth(), object.getSize().getHeight(), model.getScaleFactor(), model.getX(), model.getY());
        }
    }


    private void drawImage(Graphics g, String imgName, int x, int y, int dx, int dy, int scale, int viewX, int viewY) {
        Image img = model.getImageStore().getImage(imgName);
        if (img == null) return;

        int newX = scale * (x - viewX - dx/2) / 100;
        int newY = scale * (y - viewY - dy/2) / 100;
        int newDx = scale * dx / 100;
        int newDy = scale * dy / 100;

        g.drawImage(img, newX, newY, newDx, newDy, null);
    }

}
