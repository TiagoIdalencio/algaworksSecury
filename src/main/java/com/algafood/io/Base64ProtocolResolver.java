package com.algafood.io;

import java.util.Base64;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class Base64ProtocolResolver implements ProtocolResolver, 
	ApplicationListener<ApplicationContextInitializedEvent> {
	
	@Override
	public Resource resolve(String path, ResourceLoader resourceLoader) {
		if (path.startsWith("base64:")) {
			byte[] decodedResource = Base64.getDecoder().decode(path.substring(7));
			return  new ByteArrayResource(decodedResource);
		}
		return null;
	}

	@Override
	public void onApplicationEvent(ApplicationContextInitializedEvent event) {
		event.getApplicationContext().addApplicationListener(this);
	}

}
