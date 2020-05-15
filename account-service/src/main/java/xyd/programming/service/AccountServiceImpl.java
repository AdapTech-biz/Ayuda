package xyd.programming.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyd.programming.entity.Account;
import xyd.programming.repository.AccountRepository;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final Random random = new Random();
    @Autowired
    private final AccountRepository<Account> accountRepository;


    private TransactionAction send = (double balance, double amount) -> balance - amount;
    private TransactionAction receive = (double balance, double amount) -> balance + amount;

    public AccountServiceImpl(AccountRepository<Account> accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Long generateAccountId () {
        return random.nextLong();
    }

    @Override
    public Account createAccount(Long ownerId) {
        Account account = new Account(ownerId);
        account.setAccountId(generateAccountId());
        return account;
    }

    @Override
    public Account findAccountByOwnerId(Long ownerId) throws Exception {
        List<Account> results = this.accountRepository.findAccountByOwnerId(ownerId);

        if(!results.isEmpty())
            return results.get(0);
        else throw new Exception("No account found with ID");

    }

    @Override
    public boolean startTransaction(Long assignor, Long assignee, double payoutAmount) {
        try{
            Account assignorAccount = findAccountByOwnerId(assignor);
            Account assigneeAccount = findAccountByOwnerId(assignee);

            if(checkOverdraw(assignorAccount.getBalance(), payoutAmount)) {

                assignorAccount.setBalance(updateBalance(assignorAccount.getBalance(), payoutAmount, send));
                assigneeAccount.setBalance(updateBalance(assigneeAccount.getBalance(), payoutAmount, receive));
                return  this.accountRepository.updateAccount(assigneeAccount) && this.accountRepository.updateAccount(assignorAccount);

            } else throw new Exception("Insufficient funds to complete payout");


        } catch (Exception exception) {
            log.debug(exception.getMessage());
            return false;
        }


    }

    public boolean checkOverdraw(double balance, double payout){
        return balance >= payout;
    }

    @Override
    public double updateBalance(double balance, double payment, TransactionAction transactionAction) {
        return transactionAction.submit(balance, payment);
    }
}
