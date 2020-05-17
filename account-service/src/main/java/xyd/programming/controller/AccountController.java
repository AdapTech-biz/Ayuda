package xyd.programming.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyd.programming.entity.Account;
import xyd.programming.entity.PayoutTicket;
import xyd.programming.service.AccountService;

@Slf4j
@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/new/{id}")
    public Long createAccount(@PathVariable(value = "id", required = true) Long ownerId){

        Account account = this.accountService.createAccount(ownerId);

        log.info("New account created: {}", account);
        //store Account
        return account.getAccountId();

    }

    @PostMapping("/transaction")
    public boolean transaction(@RequestBody PayoutTicket payoutTicket) {
        return this.accountService.startTransaction(payoutTicket.getAssignor(),
                payoutTicket.getAssignee(), payoutTicket.getPayout());
    }

}
