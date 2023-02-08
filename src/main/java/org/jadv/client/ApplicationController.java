package org.jadv.client;

import lombok.Getter;
import org.jadv.services.ImageService;
import org.jadv.services.LevelService;

public class ApplicationController {

    private final ImageService imageService;

    private final LevelService levelService;
    @Getter
    private final ApplicationModel model;

    public ApplicationController(ImageService imageService, LevelService levelService) {
        this.imageService = imageService;
        this.levelService = levelService;
        model = new ApplicationModel();
    }

    public void show() {

    }
}
