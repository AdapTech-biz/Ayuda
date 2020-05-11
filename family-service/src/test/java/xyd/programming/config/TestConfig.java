package xyd.programming.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import xyd.programming.repository.FamilyMemberRepository;

@Profile("test")
@Configuration
public class TestConfig {

    @Bean
    @Primary
    public FamilyMemberRepository parentRepository() {
        return Mockito.mock(FamilyMemberRepository.class);
    }

}