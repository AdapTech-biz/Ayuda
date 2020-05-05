package xyd.programming.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import xyd.programming.entity.Account;
import xyd.programming.service.AccountService;

@Slf4j
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/new/{id}")
    public Long createAccount(@PathVariable(value = "id", required = true) Long ownerId){

        Account account = new Account(ownerId);

        accountService.generateAccountId(account);
        log.info("New account created: {}", account);
        //store Account
        return account.getAccountId();


    }
}
