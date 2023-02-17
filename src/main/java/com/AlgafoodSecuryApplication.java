package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.algafood.infrastructure.repository.CustomJpaRepositoryImpl;


@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class) 
public class AlgafoodSecuryApplication { 

	public static void main(String[] args) {
		 SpringApplication.run(AlgafoodSecuryApplication.class, args);
	}

}
