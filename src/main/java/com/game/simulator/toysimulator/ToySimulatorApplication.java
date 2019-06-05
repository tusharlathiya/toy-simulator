package com.game.simulator.toysimulator;

import com.game.simulator.model.Deshboard;
import com.game.simulator.model.Game;
import com.game.simulator.model.Robot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToySimulatorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ToySimulatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Deshboard deshboard = new Deshboard(5,5);
		Robot robot         = new Robot();
		Game game           = new Game(deshboard,robot);
		System.out.println("********Toy Robot Simulator*******\n");
		game.start();
	}
}

