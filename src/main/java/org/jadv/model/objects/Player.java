package org.jadv.model.objects;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.jadv.model.Container;
import org.jadv.model.Position;
import org.jadv.model.move.Movement;

import java.io.Serial;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Instance of a player.
 */
@ToString
@Log4j2
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
        log.info(MessageFormat.format("Player {0} moving to {1},{2}", getName(), x,  y));
        movement = new Movement(new Position(x, y, getPosition().getParent()));
    }

    /**
     * Player does next step.
     * <p>
     *     This methode is calls automatically by the engine.
     * </p>
     */
    public void move() {
        if (movement != null) {
            var newPosition = movement.getStepTarget(stepsize, getPosition());
            log.info(MessageFormat.format("Player {0} moving from {1} to {2}", getName(), getPosition(), newPosition));
            setPosition(newPosition);
            if (movement.isDone()) {
                log.info(MessageFormat.format("Player {0} finished Movement.", getName()));
                movement = null;
            }
        }
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
