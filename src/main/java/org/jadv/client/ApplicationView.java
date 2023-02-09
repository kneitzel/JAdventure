package org.jadv.client;

import org.jadv.framework.View;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Application View
 */
public class ApplicationView extends View {

    /**
     * Window of this game view.
     */
    private final JFrame gameFrame;
    private LevelPanel levelPanel;

    public ApplicationView() {
        gameFrame = new JFrame();
        gameFrame.setSize(500, 400);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public ApplicationModel getModel() {
        return (ApplicationModel) model;
    }

    @Override
    public void init() {
        levelPanel = new LevelPanel(getModel());
        gameFrame.setContentPane(levelPanel);
        gameFrame.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e Key event to handle.
             */
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                switch (e.getKeyChar()) {
                    case 'w' -> doAction(ApplicationCommands.MOVE_UP);
                    case 's' -> doAction(ApplicationCommands.MOVE_DOWN);
                    case 'a' -> doAction(ApplicationCommands.MOVE_LEFT);
                    case 'd' -> doAction(ApplicationCommands.MOVE_RIGHT);
                    case 'o' -> doAction(ApplicationCommands.SCALE_IN);
                    case 'p' -> doAction(ApplicationCommands.SCALE_OUT);
                }

            }
        });
        levelPanel.setFocusable(true);
        gameFrame.setFocusable(true);
    }

    @Override
    public void show() {
        updateView();
        gameFrame.setVisible(true);
    }

    @Override
    public void updateView() {
        gameFrame.repaint();
    }

    @Override
    public void updateModel() {

    }
}
