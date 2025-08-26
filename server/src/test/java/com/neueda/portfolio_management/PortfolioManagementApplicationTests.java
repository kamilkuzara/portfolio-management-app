package com.neueda.portfolio_management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PortfolioManagementApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		// Basic smoke test: context should start and be non-null
		assertNotNull(context, "Spring application context should have started");

		// Ensure the main application configuration class is present in the context.
		// This is a lightweight check that the application's primary configuration was registered.
		assertNotNull(context.getBean(PortfolioManagementApplication.class),
				"PortfolioManagementApplication should be a bean in the Spring context");
	}
}
