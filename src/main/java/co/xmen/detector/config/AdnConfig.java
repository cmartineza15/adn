package co.xmen.detector.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.adn")
public class AdnConfig {
    private String pattern;
    private int size;
    private int mutantSucces;
    private int mutantOccur;
}
