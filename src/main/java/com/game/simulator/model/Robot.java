package com.game.simulator.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import static com.game.simulator.helper.DirectionHelper.findDirection;
import static com.game.simulator.helper.DirectionHelper.getDirectionConversionMap;
import static com.game.simulator.helper.RobotMoveHelper.getMoveRobotInDirectionMap;

/**
 * This class helps robot to move and rotate.
 * Robot holds position on deshboard.
 */
public class Robot {
    private Position position;
    private Map<Direction,Function<Position,Position>> moveRobotInDirectionMap;
    private Map<Direction,Integer> directionConversionMap;
    private Map<String,BiFunction<Integer,Map<Direction,Integer>,Integer>> rotateRobotMap;

    public Robot() {
        this.directionConversionMap = getDirectionConversionMap();
        this.moveRobotInDirectionMap = getMoveRobotInDirectionMap();
        rotateRobotMap = new HashMap<>();
        initRotateRobotMap(rotateRobotMap);
    }

    public Position getPosition() { return this.position; }

    /**
     *  This method provide behaviour for rotation : LEFT and RIGHT.
     * @param rotateRobotMap
     */
    private void initRotateRobotMap(Map<String,
                                    BiFunction<Integer,Map<Direction,Integer>,Integer>> rotateRobotMap) {
        rotateRobotMap.put("LEFT",(currentIntDirection,directionConversionMap) ->
                (currentIntDirection - 1) < 0 ? directionConversionMap.size() - 1:(currentIntDirection - 1));
        rotateRobotMap.put("RIGHT",(currentIntDirection,directionConversionMap) ->
                (currentIntDirection+1) % directionConversionMap.size());
    }

    /**
     *  This is generic method which take rotation : LEFT or RIGHT and find behaviour from rotateRobotMap.
     * @param rotate
     */
    private void turn(String rotate) {
        if(Objects.nonNull(this.position)) {
            int currentIntDirection = this.directionConversionMap.get(this.position.getDirection());
            int newIntDirection = this.rotateRobotMap.get(rotate).apply(currentIntDirection,directionConversionMap);
            Optional<Direction> direction = findDirection(newIntDirection);
            this.position = new Position(this.position.getX(), this.position.getY(), direction.get());
        }
    }

    /**
     * This method helps robot to turn right side.
     */
    public void turnRight() { turn("RIGHT"); }

    /**
     *  This method helps robot to turn left side.
     */
    public void turnLeft() { turn("LEFT"); }

    /**
     *  This method helps to move robot by one position.
     */
    public void move() {
        if(Objects.nonNull(this.position)) {
            Position position = this.getPosition();
            Function<Position, Position> move = moveRobotInDirectionMap.get(position.getDirection());
            this.position = move.apply(position);
        }
    }

    /**
     * This method is used to print robot's current position.
     */
    public void position() {
        System.out.println(this.position.getX()+","+this.position.getY()+","+this.position.getDirection());
    }

    /**
     *  This function is used to place robot on deshboard.
     * @param position
     */
    public void place(Position position) { this.position = position; }
}
