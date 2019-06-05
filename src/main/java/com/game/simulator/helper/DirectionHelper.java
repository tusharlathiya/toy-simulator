package com.game.simulator.helper;

import com.game.simulator.model.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *  This class provide helper methods which gives conversation map
 *  from direction to it's representation in integer
 *  and find direction from it's integer representation to Enum.
 */
public class DirectionHelper {

    private static final Map<Direction,Integer> directionConversionMap = new HashMap<>();

    static {
        directionConversionMap.put(Direction.NORTH, 0);
        directionConversionMap.put(Direction.EAST,  1);
        directionConversionMap.put(Direction.SOUTH, 2);
        directionConversionMap.put(Direction.WEST,  3);
    }

    /**
     *  This map help to provide direction to Integer representation.
     */
    public static Map<Direction,Integer> getDirectionConversionMap() {
        return directionConversionMap;
    }

    /**
     *  This method helps to give Direction from it's integer representation.
     * @param newDirection
     * @return
     */
    public static Optional<Direction> findDirection(Integer newDirection) {
        for (Map.Entry<Direction, Integer> entry : directionConversionMap.entrySet()) {
            if (newDirection.equals(entry.getValue())) {
                return Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }
}
