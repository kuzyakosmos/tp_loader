package tp_loader.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeansConfig {

    @Bean
    public List<String> companies() {
        return new ArrayList<String>();
    }
}
