# Toy Simulator

## How to run application?
There are two ways to run application.
1. Go to Project root directory (toy-simulator).

Build project using following command
```bash
gradle clean build
```
Go to `build/libs` directory.
Use command `java -jar toy-simulator-0.0.1-SNAPSHOT.jar` to start game.

2. Go to `toy-simulator/src/main/java/com/game/simulator/toysimulator` directory.
Execute `ToySimulatorApplication.java` file.
   
## Notes:
1. Sample data moves are provided in test-data.txt file.
2. When application starts type command and press enter key after each command. This game will continue till you don't press `REPORT` command or game gets crashed.
