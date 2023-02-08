package org.jadv.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jadv.model.level.Level;

@Getter
@Setter
@ToString
public class ApplicationModel {

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
}
