package com.game.simulator.model;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class GameUnitTest {

    @Test
    public void testShouldAbleToProcessLeftCommandAndInvokeTurnLeftOfRobot() {
        Robot robot         = mock(Robot.class);
        Deshboard deshboard = new Deshboard(5,5);
        Game game           = new Game(deshboard,robot);

        game.execute(Command.LEFT, null);

        verify(robot).turnLeft();
    }

    @Test
    public void testShouldAbleToProcessRightCommandAndInvokeTurnRightOfRobot() {
        Robot robot         = mock(Robot.class);
        Deshboard deshboard = new Deshboard(5,5);
        Game game           = new Game(deshboard,robot);

        game.execute(Command.RIGHT, null);

        verify(robot).turnRight();
    }

    @Test
    public void testShouldAbleToProcessMoveCommandAndInvokeMoveMethodOfRobot() {
        Robot robot         = new Robot();
        Deshboard deshboard = new Deshboard(5,5);
        Robot spy           = spy(robot);
        Game game           = new Game(deshboard,spy);

        spy.place(new Position(0,0, Direction.NORTH));

        game.execute(Command.MOVE,null);

        verify(spy).move();
    }

    @Test
    public void testShouldBeAbleToProcessMoveCommandAndDoNotInvokeMoveOfRobotWhenMoveIsInvalid() {
        Robot robot         = new Robot();
        Deshboard deshboard = new Deshboard(5,5);
        Robot spyRobot      = spy(robot);
        Game game           = new Game(deshboard,spyRobot);

        spyRobot.place(new Position(5,5, Direction.NORTH));

        game.execute(Command.MOVE, null);

        verify(spyRobot, never()).move();
    }

    @Test
    public void testShouldAbleToProcessPlaceCommandAndInvokePlaceMethodOfRobot() {
        Robot robot         = mock(Robot.class);
        Deshboard deshboard = new Deshboard(5,5);
        Game game           = new Game(deshboard,robot);

        game.execute(Command.PLACE, new String[]{"0", "0", "NORTH"});

        verify(robot).place(new Position(0,0,Direction.NORTH));
    }

    @Test
    public void testShouldAbleToProcessPlaceCommandAndNotInvokePlaceOfRobotWhenInvalidPlaceCommand() {
        Robot robot         = mock(Robot.class);
        Deshboard deshboard = new Deshboard(5,5);
        Game game           = new Game(deshboard,robot);

        game.execute(Command.PLACE, new String[]{"7", "8", "NORTH"});

        verify(robot, never()).place(new Position(7,8,Direction.NORTH));
    }

    @Test
    public void testShouldAbleToProcessPlaceCommandAndNotInvokePlaceOfRobotWhenPlaceIsNegative() {
        Robot robot         = mock(Robot.class);
        Deshboard deshboard = new Deshboard(5,5);
        Game game           = new Game(deshboard,robot);

        game.execute(Command.PLACE,  new String[]{"-2", "-3", "NORTH"});

        verify(robot, never()).place(new Position(-2,-3,Direction.NORTH));
    }

    @Test
    public void testShouldAbleToProcessReportCommandAndInvokePositionOfRobot() {
        Robot robot         = new Robot();
        Deshboard deshboard = new Deshboard(5,5);
        Robot spy           = spy(robot);
        Game game           = new Game(deshboard,spy);

        spy.place(new Position(0,0, Direction.NORTH));

        game.execute(Command.REPORT,null);

        verify(spy).position();
    }
}
