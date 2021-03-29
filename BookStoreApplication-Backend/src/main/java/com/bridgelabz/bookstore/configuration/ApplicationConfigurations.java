
package com.bridgelabz.bookstore.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfigurations  {
	@Bean
	public BCryptPasswordEncoder getPasswordEncryption() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ModelMapper getModelMapper()
	{
		return new ModelMapper();
	}
	
}
