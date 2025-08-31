package danla.checkboo.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import danla.checkboo.config.property.CryptProperty;

@Configuration
@EnableConfigurationProperties({
	CryptProperty.class
})
public class PropertyConfig {
}
