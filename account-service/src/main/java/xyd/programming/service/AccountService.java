package xyd.programming.service;

import xyd.programming.entity.Account;
import xyd.programming.entity.PayoutTicket;

public interface AccountService {

    Long generateAccountId ();
    Account createAccount (Long ownerId);
    Account findAccountByOwnerId (Long ownerId) throws Exception;
    boolean startTransaction(PayoutTicket payoutTicket);
    double updateBalance(double balance, double payment, TransactionAction transactionAction);
}
