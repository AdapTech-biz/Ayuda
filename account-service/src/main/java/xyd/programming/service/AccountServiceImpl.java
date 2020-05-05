package xyd.programming.service;

import org.springframework.stereotype.Service;
import xyd.programming.entity.Account;

import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {

    private final Random random = new Random();

    @Override
    public void generateAccountId (Account forAccount) {
        forAccount.setAccountId(random.nextLong());
    }
}
