package com.game.simulator.model;

import java.util.Objects;

/**
 * This is deshboard representation of game.
 */
public class Deshboard {
    private int width;
    private int height;

    /**
     * Throw Assertion Error If height or width less than or equal to zero.
     * @param width
     * @param height
     */
    public Deshboard(int width, int height) {
        if(width <= 0 || height <= 0) throw new AssertionError("Deshboard construct can not be negative.");
        this.width = width;
        this.height = height;
    }

    /**
     *  This method helps to check whether given position is valid for given deshboard.
     * @param position
     * @return
     */
    public boolean isValidPosition(Position position){
        if(Objects.isNull(position)) return false;

        return position.getX() >= 0 && this.width >= position.getX()
                && position.getY() >= 0 && this.height >= position.getY();
    }
}
