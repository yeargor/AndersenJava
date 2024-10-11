package Homework11.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class ApplicationConfig {

    @Bean
    @ConditionalOnProperty(name = "app.conditional.bean.enabled", havingValue = "true")
    public String thisIsMyFirstConditionalBean(){
        return "bean is enabled!";
    }
}
