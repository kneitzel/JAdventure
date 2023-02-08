package org.jadv.client;

import org.jadv.services.ImageService;
import org.jadv.services.LevelService;

public class Main {
    public static void main(String[] args) {

        ApplicationController controller =  new ApplicationController(new ImageService(), new LevelService());
        controller.show();
    }
}