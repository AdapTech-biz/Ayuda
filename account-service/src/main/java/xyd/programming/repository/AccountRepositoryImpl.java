package xyd.programming.repository;

import xyd.programming.entity.Account;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountRepositoryImpl implements AccountRepository<Account> {

    private final Map<Long, Account> accountDataSource = new Hashtable<>();


    @Override
    public List<Account> findAccountById(List<Long> accountIDs) {

        return  accountDataSource.values().stream().filter((account) -> accountIDs.contains(account.getAccountId()))
                .collect(Collectors.toList());
//        return accountDataSource.get(id);
    }

    @Override
    public List<Account> findAccountByOwnerId(Long ownerID) {
        return  accountDataSource.values().stream().filter((account) -> account.getOwnerId() == ownerID)
                .collect(Collectors.toList());
    }

    @Override
    public boolean saveAccount(Account account) {
        accountDataSource.putIfAbsent(account.getAccountId(), account);
        return accountDataSource.containsKey(account.getAccountId());
    }

    @Override
    public boolean updateAccount(Account account) {
        accountDataSource.replace(account.getAccountId(), account);
        return true;
    }

    @Override
    public int count() {
        return accountDataSource.size();
    }

    @Override
    public List<Account> findAll() {
        return new ArrayList<Account>(accountDataSource.values());
    }

    @Override
    public boolean exist(Long id) {
        return accountDataSource.containsKey(id);
    }

    @Override
    public boolean delete(Long id) {
        accountDataSource.remove(id);
        return !accountDataSource.containsKey(id);
    }
}
