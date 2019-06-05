package com.game.simulator.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class DeshboardUnitTest {

    @Test
    public void testDeshboardShouldReturnTrueWhenPositionIsValid() {
        Deshboard deshboard = new Deshboard(5,5);

        assertTrue(deshboard.isValidPosition(new Position(2,3, Direction.NORTH)));
    }

    @Test
    public void testDeshboardShouldReturnFalseWhenPositionIsInValid() {
        Deshboard deshboard = new Deshboard(5,5);

        assertFalse(deshboard.isValidPosition(new Position(7,7, Direction.NORTH)));
    }

    @Test
    public void testDeshboardShouldReturnFalseWhenPositionIsNull() {
        Deshboard deshboard = new Deshboard(5,5);

        assertFalse(deshboard.isValidPosition(null));
    }

    @Test
    public void testDeshboardShouldReturnFalseWhenPositionHasNegativeValue() {
        Deshboard deshboard = new Deshboard(5,5);

        assertFalse(deshboard.isValidPosition(new Position(-2,-3,Direction.NORTH)));
    }
}
