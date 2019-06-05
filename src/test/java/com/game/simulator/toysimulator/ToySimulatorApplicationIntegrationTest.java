package com.game.simulator.toysimulator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.*;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class ToySimulatorApplicationIntegrationTest {
    @Rule
    public final SystemErrRule errorLog = new SystemErrRule().enableLog();
    @Rule
    public final SystemOutRule outputLog = new SystemOutRule().enableLog();
    @Rule
    public final TextFromStandardInputStream commandProvider = emptyStandardInputStream();

    @Test
    public void testGameShouldTellRobotIsNotPlacedIfReportCommandIsCalledBeforePlace() {
        commandProvider.provideLines("REPORT");
        ToySimulatorApplication.main(new String[]{});
        String actualOutput = stripHeaderFromOutput(outputLog.getLog());
        assertEquals("Robot is not placed yet.", stripArrowsFromOutput(actualOutput));
    }

    @Test
    public void testGameShouldIgnoreAllCommandIfPlaceCommandIsNotCalled() {
        commandProvider.provideLines("LEFT","RIGHT","MOVE","REPORT");
        ToySimulatorApplication.main(new String[]{});
        String actualOutput = stripHeaderFromOutput(outputLog.getLog());
        assertEquals("Robot is not placed yet.", stripArrowsFromOutput(actualOutput));
    }

    @Test
    public void testRobotShouldNotFallFromDeshboardIfMoveIsExecuted() {
        commandProvider.provideLines("PLACE 5,5,NORTH","MOVE","REPORT");
        ToySimulatorApplication.main(new String[]{});
        String actualOutput = stripHeaderFromOutput(outputLog.getLog());
        assertEquals("5,5,NORTH", stripArrowsFromOutput(actualOutput));
    }

    @Test
    public void testRobotIsPlacedOnCorrectPositionAndGiveCorrectReport() {
        commandProvider.provideLines("PLACE 0,0,NORTH","REPORT");
        ToySimulatorApplication.main(new String[]{});
        String actualOutput = stripHeaderFromOutput(outputLog.getLog());
        assertEquals("0,0,NORTH", stripArrowsFromOutput(actualOutput));
    }

    @Test
    public void testRobotMoveCorrectlyWithValidCommandAndGiveCorrectReport() {
        commandProvider.provideLines("PLACE 1,2,EAST","MOVE","MOVE","LEFT","MOVE","RIGHT","REPORT");
        ToySimulatorApplication.main(new String[]{});
        String actualOutput = stripHeaderFromOutput(outputLog.getLog());
        assertEquals("3,3,EAST", stripArrowsFromOutput(actualOutput));
    }

    @Test
    public void testRobotIgnoresInvalidCommand() {
        commandProvider.provideLines("ROGER","FROM","HOUSTON","REPORT");
        ToySimulatorApplication.main(new String[]{});
        String actualOutput = stripHeaderFromOutput(outputLog.getLog());
        assertEquals("Robot is not placed yet.", stripArrowsFromOutput(actualOutput));
    }

    @Test
    public void testRobotClearsCommandBetweenTwoPlaceCommandAndGiveCorrectReport() {
        commandProvider.provideLines("PLACE 1,1,SOUTH","MOVE","RIGHT","PLACE 2,3,NORTH","MOVE","REPORT");
        ToySimulatorApplication.main(new String[]{});
        String actualOutput = stripHeaderFromOutput(outputLog.getLog());
        assertEquals("2,4,NORTH", stripArrowsFromOutput(actualOutput));
    }

    @Test
    public void testRobotIsPlacedOnInvalidPosition() {
        commandProvider.provideLines("PLACE 6,6,SOUTH","REPORT");
        ToySimulatorApplication.main(new String[]{});
        String actualOutput = stripHeaderFromOutput(outputLog.getLog());
        System.out.println("****************"+outputLog.getLog());
        assertEquals("Robot is not placed yet.", stripArrowsFromOutput(actualOutput));
    }

    @Test(expected = RuntimeException.class)
    public void testGameCrash() {
        commandProvider.provideLines("PLACE ROBOT");
        ToySimulatorApplication.main(new String[]{});
    }

    private String stripHeaderFromOutput(String input) {
        return input.replace("********Toy Robot Simulator*******\n","").trim();
    }

    private String stripArrowsFromOutput(String input){
        return input.replaceAll(">","").trim();
    }
}
