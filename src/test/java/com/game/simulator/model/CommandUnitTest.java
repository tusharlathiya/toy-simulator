package com.game.simulator.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandUnitTest {

    @Test
    public void testReturnTrueIfGivenCommandIsValid() {
        assertTrue(Command.isValid("REPORT"));
    }

    @Test
    public void testReturnFalseIfGivenCommandIsNotValid() {
        assertFalse(Command.isValid("KILL"));
    }
}
