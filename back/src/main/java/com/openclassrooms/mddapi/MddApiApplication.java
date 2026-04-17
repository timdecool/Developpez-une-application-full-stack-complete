package com.openclassrooms.mddapi;

import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MddApiApplication {

	public static void main(String[] args) {
        SpringApplication.run(MddApiApplication.class, args);
	}
}
