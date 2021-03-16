package com.energytrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
/**
 * 
 * @author Soumyajit
 *
 */
@SpringBootApplication
@EnableJpaAuditing
public class EnergyTradeForeCastApplication {

	public static void main(String[] args) {
		System.out.println("Starting Energy Trade Forecast Main");
		//System.setProperty("server.servlet.context-path");
		SpringApplication.run(EnergyTradeForeCastApplication.class, args);
		//, "/inmo"
		
	}
}
