package org.jadv.client;

import org.jadv.services.ImageService;
import org.jadv.services.LevelService;

/**
 * Main of the JAdventure Client..
 */
public class Main {

    /**
     * Startpoint of the JAdventure client.
     * @param args Commandline arguments, not used.
     */
    public static void main(String[] args) {
        ImageService imageService = new ImageService();
        LevelService levelService = new LevelService();
        ApplicationController controller =  new ApplicationController(imageService, levelService);
        controller.show();
    }
}