package danla.checkboo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import danla.checkboo.config.property.CryptProperty;
import danla.checkboo.utility.AesEncryptor;

@Configuration
public class CryptConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AesEncryptor aesEncryptor(CryptProperty cryptProperty) {
		return new AesEncryptor(cryptProperty.key());
	}
}
