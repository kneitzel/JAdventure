package org.jadv.model.move;

import org.jadv.model.Position;

import java.util.function.Function;

/**
 * Movement to a target.
 */
public class Movement {

    /**
     * Target of the movement.
     */
    private Position target;

    /**
     * Creates a new instance of Movement.
     * @param target Target to move to.
     */
    public Movement(Position target) {
        this.target = target;
    }

    /**
     * Get target of next step.
     * @param stepSize Size of Step.
     * @param currentPosition Current Position
     * @return new Position after the step.
     */
    public Position getStepTarget(double stepSize, Position currentPosition) {
        final double dx = target.getX() - currentPosition.getX();
        final double dy = target.getY() - currentPosition.getY();
        final double distance = Math.sqrt(dx*dx + dy*dy);
        if (distance <= stepSize) return target;

        final double deltaX = dx*stepSize/distance;
        final double deltyY = dy*stepSize/distance;

        return new Position(currentPosition.getX()+deltaX, currentPosition.getY()+deltyY, currentPosition.getParent());
    }
}
