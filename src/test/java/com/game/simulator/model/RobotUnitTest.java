package com.game.simulator.model;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RobotUnitTest {

    @Test
    public void testRobotShouldBeAbleToTurnRightWhenRobotIsPlaced() {
        Position robotPosition = new Position(0, 0, Direction.NORTH);
        Robot robot = new Robot();

        robot.place(robotPosition);
        robot.turnRight();
        Position actualPosition   = robot.getPosition();
        Position expectedPosition = new Position(0, 0, Direction.EAST);

        assertEquals(actualPosition, expectedPosition);
    }

    @Test
    public void testRobotShouldNotAbleToTurnRightWhenRobotIsNotPlaced() {
        Robot robot = new Robot();

        robot.turnRight();
        Position actualPosition   = robot.getPosition();

        assertNull(actualPosition);
    }

    @Test
    public void testRobotShouldBeAbleToTurnRightWhenDirectionIsWestAndRobotIsPlaced() {
        Position robotPosition = new Position(0, 0, Direction.WEST);
        Robot robot = new Robot();

        robot.place(robotPosition);
        robot.turnRight();
        Position actualPosition   = robot.getPosition();
        Position expectedPosition = new Position(0, 0, Direction.NORTH);

        assertEquals(actualPosition, expectedPosition);
    }

    @Test
    public void testRobotShouldBeAbleToTurnLeftWhenRobotIsPlaced() {
        Position robotPosition = new Position(0, 0, Direction.EAST);
        Robot robot = new Robot();

        robot.place(robotPosition);
        robot.turnLeft();
        Position actualPosition   = robot.getPosition();
        Position expectedPosition = new Position(0, 0, Direction.NORTH);

        assertEquals(actualPosition, expectedPosition);
    }

    @Test
    public void testRobotShouldNotAbleToTurnLeftWhenRobotIsNotPlaced() {
        Robot robot = new Robot();

        robot.turnLeft();
        Position actualPosition   = robot.getPosition();

        assertNull(actualPosition);
    }

    @Test
    public void testRobotShouldBeAbleToTurnLeftWhenDirectionIsNorthAndRobotIsPlaced() {
        Position robotPosition = new Position(0, 0, Direction.NORTH);
        Robot robot = new Robot();

        robot.place(robotPosition);
        robot.turnLeft();
        Position actualPosition   = robot.getPosition();
        Position expectedPosition = new Position(0, 0, Direction.WEST);

        assertEquals(actualPosition, expectedPosition);
    }

    @Test
    public void testRobotShouldBeAbleToMoveInNorthWhenRobotIsPlaced() {
        Position robotPosition = new Position(0, 0, Direction.NORTH);
        Robot robot = new Robot();

        robot.place(robotPosition);
        robot.move();

        Position actualPosition   = robot.getPosition();
        Position expectedPosition = new Position(0, 1, Direction.NORTH);

        assertEquals(actualPosition, expectedPosition);
    }

    @Test
    public void testRobotShouldNotAbleToMoveInNorthWhenRobotIsNotPlaced() {
        Robot robot = new Robot();
        robot.move();

        Position actualPosition   = robot.getPosition();

        assertNull(actualPosition);
    }

    @Test
    public void testRobotShouldBeAbleToMoveInEastWhenRobotIsPlaced() {
        Position robotPosition = new Position(0, 0, Direction.EAST);
        Robot robot = new Robot();

        robot.place(robotPosition);
        robot.move();

        Position actualPosition   = robot.getPosition();
        Position expectedPosition = new Position(1, 0, Direction.EAST);

        assertEquals(actualPosition, expectedPosition);
    }

    @Test
    public void testRobotShouldNotAbleToMoveInEastWhenRobotIsNotPlaced() {
        Robot robot = new Robot();
        robot.move();

        Position actualPosition   = robot.getPosition();

        assertNull(actualPosition);
    }

    @Test
    public void testRobotShouldBeAbleToMoveInWestWhenRobotIsPlaced() {
        Position robotPosition = new Position(1, 0, Direction.WEST);
        Robot robot = new Robot();

        robot.place(robotPosition);
        robot.move();

        Position actualPosition   = robot.getPosition();
        Position expectedPosition = new Position(0, 0, Direction.WEST);

        assertEquals(actualPosition, expectedPosition);
    }

    @Test
    public void testRobotShouldNotAbleToMoveInWestWhenRobotIsNotPlaced() {
        Robot robot = new Robot();

        robot.move();

        Position actualPosition   = robot.getPosition();

        assertNull(actualPosition);
    }

    @Test
    public void testRobotShouldBeAbleToMoveInSouthWhenRobotIsPlaced() {
        Position robotPosition = new Position(0, 1, Direction.SOUTH);
        Robot robot = new Robot();

        robot.place(robotPosition);
        robot.move();

        Position actualPosition   = robot.getPosition();
        Position expectedPosition = new Position(0, 0, Direction.SOUTH);

        assertEquals(actualPosition, expectedPosition);
    }

    @Test
    public void testRobotShouldNotAbleToMoveInSouthWhenRobotIsNotPlaced() {
        Robot robot = new Robot();

        robot.move();

        Position actualPosition   = robot.getPosition();

        assertNull(actualPosition);
    }

    @Test
    public void testRobotShouldReportHisCurrentPosition() throws IOException {
        ByteArrayOutputStream consoleStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(consoleStream));
        Position position = new Position(2, 3, Direction.NORTH);
        Robot robot = new Robot();
        robot.place(position);
        robot.position();
        consoleStream.flush();
        String currentPosition = new String(consoleStream.toByteArray());
        String[] linesOfOutput = currentPosition.split(System.getProperty("line.separator"));

        assertEquals("2,3,NORTH", linesOfOutput[0]);
    }

    @Test
    public void testRobotToBePlacedOnGivenPosition() {
        Robot    robot    = new Robot();
        Position position = new Position(0,0,Direction.NORTH);

        robot.place(position);

        assertEquals(robot.getPosition(), position);
    }
}
