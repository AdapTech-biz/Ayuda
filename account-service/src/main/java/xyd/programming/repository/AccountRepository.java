package xyd.programming.repository;

import xyd.programming.entity.Account;

import java.util.List;

public interface AccountRepository<T extends Account> {

    List<T> findAccountById (List<Long> accountIDs);
    List<T> findAccountByOwnerId (Long accountIDs);
    boolean saveAccount(T account);
    boolean updateAccount(T account);
    int count();
    List<T> findAll();
    boolean exist (Long id);
    boolean delete (Long id);
}
