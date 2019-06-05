package com.game.simulator.model;

/**
 *  This is Command Enum which tells about valid command.
 *  It provides method which tells whether given command is valid or not.
 */
public enum Command {
    PLACE("PLACE"),
    MOVE("MOVE"),
    LEFT("LEFT"),
    RIGHT("RIGHT"),
    REPORT("REPORT");

    private String command;

    Command(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return this.command;
    }

    /**
     * This method helps to tell whether given command in string is valid or not
     * @param stringCommand
     * @return
     */
    public static boolean isValid(String stringCommand) {
        boolean isCommandExist = false;
        for (Command command : Command.values()) {
            if (command.name().equalsIgnoreCase(stringCommand)) {
                isCommandExist = true;
                break;
            }
        }
        return isCommandExist;
    }
}
