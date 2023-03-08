package org.jadv.client;

import org.jadv.framework.View;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Application View
 */
public class ApplicationView extends View implements KeyListener {

    /**
     * Window of this game view.
     */
    private JFrame gameFrame;

    /**
     * Gets the model with the data.
     * @return The model with the data that is shown inside the view.
     */
    @Override
    public ApplicationModel getModel() {
        return (ApplicationModel) model;
    }

    /**
     * Initializes this view.
     */
    @Override
    public void init() {
        gameFrame = new JFrame("JAdventure v0.1");
        gameFrame.setSize(500, 400);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final LevelPanel levelPanel = new LevelPanel(getModel());
        gameFrame.setContentPane(levelPanel);
        gameFrame.addKeyListener(this);
        levelPanel.setFocusable(true);
        gameFrame.setFocusable(true);
    }

    /**
     * Displays the window.
     */
    @Override
    public void show() {
        gameFrame.setVisible(true);
    }

    /**
     * Updates the view.
     */
    @Override
    public void updateView() {
        super.updateView();
        gameFrame.repaint();
    }

    /**
     * Updates the model if required.
     */
    @Override
    protected void updateModel() {
        super.updateModel();
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param event the event to be processed
     */
    @Override
    public void keyTyped(final KeyEvent event) {
        switch (event.getKeyChar()) {
            case 'w' -> sendAction(ApplicationCommands.MOVE_UP);
            case 's' -> sendAction(ApplicationCommands.MOVE_DOWN);
            case 'a' -> sendAction(ApplicationCommands.MOVE_LEFT);
            case 'd' -> sendAction(ApplicationCommands.MOVE_RIGHT);
            case 'o' -> sendAction(ApplicationCommands.SCALE_IN);
            case 'p' -> sendAction(ApplicationCommands.SCALE_OUT);
        }
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param event the event to be processed
     */
    @Override
    public void keyPressed(final KeyEvent event) {
        // Nothing to do on key press.
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param event the event to be processed
     */
    @Override
    public void keyReleased(final KeyEvent event) {
        // Nothing to do on key release.
    }
}
