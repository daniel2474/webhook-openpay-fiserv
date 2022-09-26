package com.webHook;


import java.security.Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.dentrassi.crypto.pem.PemKeyStoreProvider;
 
@SpringBootApplication
public class WebHookApplication
{
	public static void main(String[] args) {
			Security.addProvider(new PemKeyStoreProvider());
			
			SpringApplication.run(WebHookApplication.class, args);
	}
}
