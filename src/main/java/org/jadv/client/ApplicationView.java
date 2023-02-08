package org.jadv.client;

import javax.swing.*;

/**
 * Application View
 */
public class ApplicationView {

    /**
     * Window of this game view.
     */
    private JFrame gameFrame;

    /**
     * Model of the view
     */
    private ApplicationModel model;

    /**
     * Controller of this view
     */
    private final ApplicationController controller;

    public ApplicationView(ApplicationModel model, ApplicationController controller) {
        this.model = model;
        this.controller = controller;
    }
}
