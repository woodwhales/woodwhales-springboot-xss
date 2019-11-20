package org.woodwhales.xss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringBootXssApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootXssApplication.class, args);
	}

}
