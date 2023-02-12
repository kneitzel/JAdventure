package org.jadv.client;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jadv.framework.Model;
import org.jadv.model.level.Level;

import java.io.Serial;
import java.io.Serializable;

/**
 * Model of the Application Window.
 */
@Getter
@Setter
@ToString
public class ApplicationModel extends Model implements Serializable {

    /**
     * Serial version UID of ApplicationModel
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Default scale factor (100%)
     */
    private static final int DEFAULT_SCALE_FACTOR = 100;

    /**
     * Current level
     */
    private Level level;

    /**
     * ScaleFactor in percent
     */
    private int scaleFactor = DEFAULT_SCALE_FACTOR;

    /**
     * X Coordinate of level in upper left corner
     */
    @SuppressWarnings("PMD.ShortVariable")
    private int x;

    /**
     * Y coordinate of level in upper left corner
     */
    @SuppressWarnings("PMD.ShortVariable")
    private int y;

    /**
     * Store for images
     */
    @Setter
    private ImageStore imageStore;
}
