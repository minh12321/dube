package com.bong.du;

import org.springframework.boot.SpringApplication;

import com.bongda.Application;

public class TestDuApplication {

	public static void main(String[] args) {
		SpringApplication.from(Application::main).with(TestcontainersConfiguration.class).run(args);
	}

}
