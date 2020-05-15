package xyd.programming.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import xyd.programming.repository.ChoreRepository;

@Profile("test")
@Configuration
public class TestConfig {

    @Bean
    @Primary
    public ChoreRepository choreRepository() {
        return Mockito.mock(ChoreRepository.class);
    }

}