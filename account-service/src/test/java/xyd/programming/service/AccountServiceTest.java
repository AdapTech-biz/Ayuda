package xyd.programming.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import xyd.programming.config.TestConfig;
import xyd.programming.entity.Account;
import xyd.programming.entity.PayoutTicket;
import xyd.programming.repository.AccountRepository;

import java.util.Collections;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
public class AccountServiceTest {

    private AccountService accountService;
    @Mock
    private AccountRepository<Account> accountRepository;

    @BeforeEach
    void setUp() {
        this.accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    void createNewAccount() {
        Account account = this.accountService.createAccount(12345L);
        Assertions.assertThat(account.getAccountId()).isNotNull();
        Assertions.assertThat(account.getOwnerId()).isEqualTo(12345L);
    }

    @Test
    //
    void givenValidAccountId_thenReturnAccount() throws Exception {
        Account account = new Account(12345L);
        Mockito.when(this.accountRepository.findAccountByOwnerId(12345L)).thenReturn(List.of(account));
        Account result = this.accountService.findAccountByOwnerId(12345L);

        Assertions.assertThat(result.getOwnerId()).isEqualTo(12345L);
    }

    @Test
    void whenLookupInvalidAccountID_thenReturnException() throws Exception {

        Mockito.when(this.accountRepository.findAccountByOwnerId(12345L)).thenReturn(Collections.emptyList());


        Assertions.assertThatThrownBy(()->this.accountService.findAccountByOwnerId(12345L))
                .hasMessage("No account found with ID");
    }

    @Test
    void whenStartingTransactionWithAppropriateFunds_thenAccountBalanceShouldUpdate() {
        Account account1 = new Account(12345L);
        account1.setBalance(200.00);
        Account account2 = new Account(6789L);
        PayoutTicket payoutTicket = new PayoutTicket(12345L, 6789L, 150.00, 9999L);
        Mockito.when(this.accountRepository.findAccountByOwnerId(12345L)).thenReturn(List.of(account1));
        Mockito.when(this.accountRepository.findAccountByOwnerId(6789L)).thenReturn(List.of(account2));

        boolean transactionStatus =  this.accountService.startTransaction(payoutTicket);
        Assertions.assertThat(transactionStatus).isTrue();
        Assertions.assertThat(account1.getBalance()).isEqualTo(50.00);
        Assertions.assertThat(account2.getBalance()).isEqualTo(150.00);



    }

    @Test
    void whenStartingTransactionWithInappropriateFunds_thenNoBalanceUpdatesPerformed() {
        Account account1 = new Account(12345L);
        Account account2 = new Account(6789L);
        PayoutTicket payoutTicket = new PayoutTicket(12345L, 6789L, 150.00, 9999L);
        Mockito.when(this.accountRepository.findAccountByOwnerId(12345L)).thenReturn(List.of(account1));
        Mockito.when(this.accountRepository.findAccountByOwnerId(6789L)).thenReturn(List.of(account2));

        boolean transactionStatus =  this.accountService.startTransaction(payoutTicket);

        Assertions.assertThat(transactionStatus).isFalse();
        Assertions.assertThat(account2.getBalance()).isEqualTo(0);


    }
}
