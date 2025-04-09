package com.bong.du;

import org.springframework.boot.SpringApplication;

public class TestDuApplication {

	public static void main(String[] args) {
		SpringApplication.from(DuApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
