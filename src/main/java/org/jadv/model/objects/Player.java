package org.jadv.model.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jadv.model.Container;
import org.jadv.model.Position;
import org.jadv.model.move.Movement;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

/**
 * Instance of a player.
 */
@ToString
public class Player extends GameObject implements Container {
    /**
     * Serial version UID of Player.
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Stepsize.
     */
    @Getter
    @Setter
    private int stepsize;

    /**
     * Objects the player carries with him.
     */
    @Getter
    private final List<GameObject> objects = new ArrayList<>();

    @Getter
    @Setter
    private transient Movement movement;

    /**
     * Creates a new instance of player.
     */
    public Player() {
        super();
    }

    /**
     * Sets the target for the player.
     * @param x x coordinate of target.
     * @param y y coordinate of target.
     */
    private void setTarget(double x, double y) {
        movement = new Movement(new Position(x, y, getPosition().getParent()));
    }

    /**
     * Player does next step.
     * <remark>
     *     This methode is calles automatically by the engine.
     * </remark>
     */
    public void move() {
        if (movement != null) setPosition(movement.getStepTarget(stepsize, getPosition()));
    }

    /**
     * Gets the List of GameObject children.
     *
     * @return List of GameObjects.
     */
    @Override
    public List<GameObject> getChildren() {
        return objects;
    }
}
