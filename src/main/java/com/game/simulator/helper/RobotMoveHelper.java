package com.game.simulator.helper;

import com.game.simulator.model.Direction;
import com.game.simulator.model.Position;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *  This class is helps to provide map that will provide function which helps robot to move
 *  in a step in particular direction.
 */
public class RobotMoveHelper {

    private static final Map<Direction,Function<Position,Position>> moveRobotInDirectionMap = new HashMap<>();

    static {
        moveRobotInDirectionMap.put(Direction.NORTH,
                (position) -> new Position(position.getX(), position.getY() + 1, Direction.NORTH));
        moveRobotInDirectionMap.put(Direction.SOUTH,
                (position) -> new Position(position.getX(), position.getY() - 1, Direction.SOUTH));
        moveRobotInDirectionMap.put(Direction.EAST,
                (position) -> new Position(position.getX() + 1, position.getY(), Direction.EAST));
        moveRobotInDirectionMap.put(Direction.WEST,
                (position) -> new Position(position.getX() - 1, position.getY(), Direction.WEST));
    }

    public static Map<Direction,Function<Position,Position>> getMoveRobotInDirectionMap(){
        return moveRobotInDirectionMap;
    }
}
