package com.narvee.usit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@SpringBootApplication
public class UsitPortalApp2Application {
	public static final Logger logger = LoggerFactory.getLogger(UsitPortalApp2Application.class);
	public static void main(String[] args) {
		//System.out.println("UsitPortalApp2Application.main()");
		logger.info("Application Started => UsitPortalApp2Application.main()");
		SpringApplication.run(UsitPortalApp2Application.class, args);
	}

}
