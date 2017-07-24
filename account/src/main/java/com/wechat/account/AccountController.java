package com.wechat.account;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AccountController {

    protected Logger logger = Logger.getLogger(AccountController.class
            .getName());

    @RequestMapping("/accounts/{accountNumber}")
    public Account byNumber(@PathVariable("accountNumber") String accountNumber) throws AccountNotFoundException {

        logger.info("accounts-service byNumber() invoked: " + accountNumber);
        //Account account = accountRepository.findByNumber(accountNumber);
        Account account = new Account();
        account.setNumber(accountNumber);
        account.setId(100000);
        logger.info("accounts-service byNumber() found: " + account);

        if (account == null)
            throw new AccountNotFoundException(accountNumber);
        else {
            return account;
        }
    }
}
