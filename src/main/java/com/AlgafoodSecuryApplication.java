package com;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.algafood.infrastructure.repository.CustomJpaRepositoryImpl;
import com.algafood.io.Base64ProtocolResolver;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodSecuryApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		
		var app = new SpringApplication(AlgafoodSecuryApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
	}

}
