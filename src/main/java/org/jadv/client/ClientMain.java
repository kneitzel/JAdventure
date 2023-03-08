package org.jadv.client;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

/**
 * Main of the JAdventure Client..
 */
public abstract class ClientMain {

    /**
     * Startpoint of the JAdventure client.
     * @param args Commandline arguments, not used.
     */
    public static void main(final String[] args) {
        JFrame frame = new JFrame("JSlider Test");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        JSlider slider = new JSlider();
        frame.add(slider);
        slider.setMinorTickSpacing(10);
//        slider.setMajorTickSpacing(10);
        slider.setSnapToTicks(true);
        frame.pack();
        frame.setVisible(true);
        slider.addPropertyChangeListener(ClientMain::handleEvent);
        slider.addChangeListener( e -> {
            if (!slider.getValueIsAdjusting()) System.out.println("On Change Listener: " + slider.getValue() + " - " + slider.getValueIsAdjusting());
        });
//        final ImageService imageService = new ImageService();
//        final LevelService levelService = new LevelService();
//        final ApplicationController controller =  new ApplicationController(imageService, levelService);
//        controller.show();
    }

    private static void handleEvent(PropertyChangeEvent propertyChangeEvent) {
        System.out.println("slider!");
    }
}