package org.jadv.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jadv.framework.Model;
import org.jadv.model.level.Level;

/**
 * Model of the Application Window.
 */
@Getter
@Setter
@ToString
public class ApplicationModel extends Model {

    /**
     * Current level
     */
    private Level level;

    /**
     * ScaleFactor in percent
     */
    private int scaleFactor = 100;

    /**
     * X Coordinate of level in upper left corner
     */
    private int x = 0;

    /**
     * Y coordinate of level in upper left corner
     */
    private int y = 0;

    /**
     * Store for images
     */
    @Setter
    private ImageStore imageStore;
}
