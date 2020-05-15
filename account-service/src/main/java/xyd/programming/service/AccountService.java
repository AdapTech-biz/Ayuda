package xyd.programming.service;

import xyd.programming.entity.Account;

public interface AccountService {

    Long generateAccountId ();
    Account createAccount (Long ownerId);
    Account findAccountByOwnerId (Long ownerId) throws Exception;
    boolean startTransaction(Long assignor, Long assignee, double payoutAmount);
    double updateBalance(double balance, double payment, TransactionAction transactionAction);
}
