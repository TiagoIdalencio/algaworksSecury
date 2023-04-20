package com.algafood.core.security.authorizationServer;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@ConfigurationProperties("algafood.jwt.keystore")
public class JwtKeyStoreProperties {

	@NotNull
	private Resource path;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String keypairAlias;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKeypairAlias() {
		return keypairAlias;
	}

	public void setKeypairAlias(String keypairAlias) {
		this.keypairAlias = keypairAlias;
	}

	public Resource getPath() {
		return path;
	}

	public void setPath(Resource path) {
		this.path = path;
	}

}