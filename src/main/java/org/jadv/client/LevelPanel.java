package org.jadv.client;

import org.jadv.model.level.Level;
import org.jadv.model.objects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

/**
 * Panel to display a level.
 */
public class LevelPanel extends JPanel {

    /**
     * Serial version UID of LevelPanel
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ApplicationModel holding all required data.
     */
    private final ApplicationModel model;

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public LevelPanel(final ApplicationModel model) {
        super();
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
     * @param graphics the <code>Graphics</code> object to protect
     * @see #paint
     */
    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);

        checkViewPoint();

        final Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setBackground(Color.BLACK);
        graphics2D.clearRect(0,0, getWidth(), getHeight());

        final Level level = model.getLevel();

        drawImage(graphics2D, level.getGraphicResource(), level.getWidth()/2, level.getHeight()/2, level.getWidth(), level.getHeight(), model.getScaleFactor(), model.getX(), model.getY());

        for (final GameObject object : level.getChildren()) {
            drawImage(graphics2D, object.getGraphicResource(), (int)object.getPosition().getX(), (int)object.getPosition().getY(), object.getSize().getWidth(), object.getSize().getHeight(), model.getScaleFactor(), model.getX(), model.getY());
        }
    }

    /**
     * Checks that the ViewPoint is valid.
     */
    private void checkViewPoint() {
        final int realHeight = model.getLevel().getHeight() * model.getScaleFactor() / 100;
        final int realWidth = model.getLevel().getWidth() * model.getScaleFactor() / 100;

        // To move: Calculate x/y
        final int maxY = realHeight - getHeight();
        final int maxX = realWidth - getWidth();
        final int currY = model.getY() * model.getScaleFactor() / 100;
        final int currX = model.getX() * model.getScaleFactor() / 100;
        if (maxY < currY && 0 <= maxY) {
            model.setY(maxY * 100 / model.getScaleFactor());
        }
        if (maxX < currX && 0 <= maxX) {
            model.setX(maxX * 100 / model.getScaleFactor());
        }
    }

    /**
     * Draws an image.
     * @param graphics Graphics to draw to.
     * @param imgName Name of image to draw.
     * @param x Position of the image in the level (x-coordinate).
     * @param y Position of the image in the Level (y-coordinate).
     * @param width Width of image.
     * @param height height od image.
     * @param scale Scale factor
     * @param viewX View x-coordinate.
     * @param viewY View y-coordinate.
     */
    @SuppressWarnings("PMD.ShortVariable")
    private void drawImage(final Graphics graphics, final String imgName, final int x, final int y, final int width,
                           final int height, final int scale, final int viewX, final int viewY) {
        final Image img = model.getImageStore().getImage(imgName);
        if (img == null) {
            return;
        }

        final int newX = scale * (x - viewX - width/2) / 100;
        final int newY = scale * (y - viewY - height/2) / 100;
        final int newDx = scale * width / 100;
        final int newDy = scale * height / 100;

        graphics.drawImage(img, newX, newY, newDx, newDy, null);
    }

}
