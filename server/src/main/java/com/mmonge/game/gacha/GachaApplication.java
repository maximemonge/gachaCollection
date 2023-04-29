package com.mmonge.game.gacha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GachaApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(GachaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GachaApplication.class, args);
	}

}
