package com.game.simulator.model;

import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import static com.game.simulator.helper.RobotMoveHelper.getMoveRobotInDirectionMap;

/**
 * This is Game class which takes command from console and execute and delegate call to domain object.
 */
public class Game {
    private Robot     robot;
    private Deshboard deshboard;
    private Map<Direction,Function<Position,Position>> robotMoverMap;

    /**
     *  Game Construct needs Deshboard and robot domain object.
     * @param deshboard
     * @param robot
     */
    public Game(Deshboard deshboard, Robot robot) {
        this.deshboard     = deshboard;
        this.robot         = robot;
        this.robotMoverMap = getMoveRobotInDirectionMap();
    }

    /**
     *  This method starts game and prepare required objects.
     */
    public void start() {
        this.prepareGame();

        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        String nextCommand;
        try {
            while (!(nextCommand = scanner.nextLine().trim().toUpperCase()).contains(Command.REPORT.toString())) {
                this.processCommand(nextCommand);
            }
            this.processCommand("REPORT");
        } catch (Exception exception){
            throw new RuntimeException("Oops!! I am crashed. See you next time.");
        }
    }

    /**
     * This method takes command in String after that validate and give execute method to execute
     * @param command
     */
    private void processCommand(String command) {
        System.out.print("> ");
        String nextCommand = command;
        String[] args      = null;
        if (nextCommand.contains(Command.PLACE.toString())) {
            String[] commandWithArgs = nextCommand.split(" ");
            nextCommand              = commandWithArgs[0];
            args                     = commandWithArgs[1].split(",");
        }
        if(Command.isValid(nextCommand)) this.execute(Command.valueOf(nextCommand), args);
    }

    private void prepareGame() {
        if(Objects.isNull(deshboard)) this.deshboard = new Deshboard(5,5);
        if(Objects.isNull(robot))     this.robot     = new Robot();
    }

    /**
     * This method will execute command given to game.
     * @param command
     * @param commandArgs
     */
    public void execute(Command command, String[] commandArgs) {
        if(Command.LEFT.equals(command)) this.robot.turnLeft();

        if(Command.RIGHT.equals(command)) this.robot.turnRight();

        if(Command.REPORT.equals(command)) this.reportRobotPosition();

        if(Command.MOVE.equals(command)) this.moveRobotToValidPosition();

        if(Command.PLACE.equals(command)
                && commandArgs.length == 3) this.placeRobotToValidPosition(commandArgs);
    }

    /**
     *  This method will check if Robot can place to valid position than place to that position.
     * @param commandArgs
     */
    private void placeRobotToValidPosition(String[] commandArgs) {
        int xPosition = Integer.parseInt(commandArgs[0]);
        int yPosition = Integer.parseInt(commandArgs[1]);
        Direction direction = Direction.valueOf(commandArgs[2]);
        Position givenPosition = new Position(xPosition, yPosition, direction);
        if(this.deshboard.isValidPosition(givenPosition)) this.robot.place(givenPosition);
    }

    /**
     * This method check if robot can move to valid position than move robot to that position.
     */
    private void moveRobotToValidPosition() {
        if(Objects.nonNull(this.robot)
                && Objects.nonNull(this.robot.getPosition())) {
            Position currentRobotPosition = this.robot.getPosition();
            Position newerPosition = robotMoverMap.get(currentRobotPosition.getDirection()).apply(currentRobotPosition);
            if (this.deshboard.isValidPosition(newerPosition)) this.robot.move();
        }
    }

    /**
     *  This method check If robot has valid position than report robot position.
     */
    private void reportRobotPosition() {
        if(Objects.nonNull(this.robot)
             && Objects.nonNull(this.robot.getPosition())) {
            this.robot.position();
        } else {
            System.out.println("Robot is not placed yet.");
        }
    }
}
