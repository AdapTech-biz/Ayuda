package xyd.programming.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import xyd.programming.entity.Account;
import xyd.programming.repository.AccountRepository;
import xyd.programming.repository.AccountRepositoryImpl;

@Profile("test")
@Configuration
public class TestConfig {

    @Bean
    public AccountRepository accountRepository() {
        return Mockito.mock(AccountRepository.class);
    }
}
