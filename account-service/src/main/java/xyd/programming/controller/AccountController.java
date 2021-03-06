package xyd.programming.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyd.programming.entity.Account;
import xyd.programming.entity.PayoutTicket;
import xyd.programming.service.AccountService;
import xyd.programming.util.Mappings;

@Slf4j
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/test")
    public String test() {
        return "Test route for Account Service";
    }

    @PostMapping(Mappings.NEW_ACCOUNT)
    public Long createAccount(@PathVariable(value = "id", required = true) Long ownerId){
        log.info("Post request to AccountController - " + Mappings.NEW_ACCOUNT);
        Account account = this.accountService.createAccount(ownerId);

        log.info("New account created: {}", account);
        //store Account
        return account.getAccountId();

    }

    // mapping to start transaction for account
    @PostMapping(Mappings.TRANSACTION)
    public boolean transaction(@RequestBody PayoutTicket payoutTicket) {
        log.info("Post request to AccountController - " + Mappings.TRANSACTION);
        return this.accountService.startTransaction(payoutTicket);
    }

}
