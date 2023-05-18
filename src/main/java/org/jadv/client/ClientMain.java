package org.jadv.client;

import org.jadv.services.ImageService;
import org.jadv.services.LevelService;

/**
 * Main of the JAdventure Client..
 */
public abstract class ClientMain {

    /**
     * Startpoint of the JAdventure client.
     * @param args Commandline arguments, not used.
     */
    public static void main(final String[] args) {
        final ImageService imageService = new ImageService();
        final LevelService levelService = new LevelService();
        final ApplicationController controller =  new ApplicationController(imageService, levelService);
        controller.show();
    }
}