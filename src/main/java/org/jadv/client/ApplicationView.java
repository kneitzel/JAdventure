package org.jadv.client;

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

    public ApplicationView(ApplicationModel model) {
        super(model);
        gameFrame = new JFrame();
        gameFrame.setSize(500, 400);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public ApplicationModel getModel() {
        return (ApplicationModel) model;
    }

    /**
     * Gets the controller. Can be implemented including a cast to a child class of Controller.
     *
     * @return The Controller.
     */
    @Override
    public ApplicationController getController() {
        return (ApplicationController) controller;
    }

    protected void move(int dx, int dy) {
        getModel().setX(Math.max(0, getModel().getX() + dx * getModel().getScaleFactor()/100));
        getModel().setY(Math.max(0, getModel().getY() + dy * getModel().getScaleFactor()/100));
        levelPanel.repaint();
    }

    protected void scale(int ds) {
        int newScale = getModel().getScaleFactor() + ds;

        if (newScale < 25) newScale = 25;
        if (newScale > 1000) newScale = 10000;

        int x = getModel().getX() * newScale / getModel().getScaleFactor();
        int y = getModel().getY() * newScale / getModel().getScaleFactor();

        getModel().setScaleFactor(newScale);
        getModel().setX(x);
        getModel().setY(y);
        levelPanel.repaint();
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
                    case 'w' -> move(0, -20);
                    case 's' -> move(0, 20);
                    case 'a' -> move(-20, 0);
                    case 'd' -> move(20, 0);
                    case 'o' -> scale(10);
                    case 'p' -> scale(-10);
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
    }

    @Override
    public void updateModel() {

    }
}
