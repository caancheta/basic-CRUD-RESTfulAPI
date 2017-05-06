/**
 * 
 */
package io.github.caancheta.practice;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author Christopher
 *
 */
@SpringBootApplication
public class App {
	
	static final Logger logger = LogManager.getLogger(App.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		logger.info("Entering the Application");
		SpringApplication.run(App.class, args);
	}

}
