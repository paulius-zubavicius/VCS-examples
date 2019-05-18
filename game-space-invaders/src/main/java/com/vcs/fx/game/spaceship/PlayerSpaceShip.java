package com.vcs.fx.game.spaceship;

import com.vcs.fx.game.model.Allies;
import com.vcs.fx.game.model.MoveDirection;
import com.vcs.fx.game.model.Rectangle;

public class PlayerSpaceShip extends SpaceShip {

    private static final int SHIP_SPEED = 1;

    private final Rectangle moveBounds;
    private MoveDirection direction;

    public PlayerSpaceShip(Rectangle rect, Allies alies, Rectangle moveBounds) {
        super(rect, alies);
        this.moveBounds = moveBounds;

    }

    public void move(MoveDirection direction) {
        this.direction = direction;
    }

    @Override
    public void doPhisics(long now) {

        switch (direction) {
            case UP: {
                boundsCheckAndMove(0, -SHIP_SPEED);
                return;
            }
            case DOWN: {
                boundsCheckAndMove(0, SHIP_SPEED);
                return;
            }
            case LEFT: {
                boundsCheckAndMove(-SHIP_SPEED, 0);
                return;
            }
            case RIGHT: {
                boundsCheckAndMove(SHIP_SPEED, 0);
                return;
            }
            default:
                return;
        }
    }

    private void boundsCheckAndMove(int xOffset, int yOffset) {

        pos.increaseX(xOffset);
        pos.increaseY(yOffset);

        if (moveBounds != null && !moveBounds.isInside(pos)) {
            pos.increaseX(-xOffset);
            pos.increaseY(-yOffset);
        }

    }

}
