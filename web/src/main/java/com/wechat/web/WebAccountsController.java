package com.wechat.web;

import com.wechat.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
public class WebAccountsController {

    @Autowired
    protected WebAccountsService accountsService;

    protected Logger logger = Logger.getLogger(WebAccountsController.class
            .getName());

    public WebAccountsController(WebAccountsService accountsService) {
        this.accountsService = accountsService;
    }


    @RequestMapping("/accounts")
    public String goHome() {
        return "index";
    }

    @RequestMapping("/accounts/{accountNumber}")
    public String byNumber(Model model,
                           @PathVariable("accountNumber") String accountNumber) {

        logger.info("web-service byNumber() invoked: " + accountNumber);

        Account account = accountsService.getByNumber(accountNumber);
        logger.info("web-service byNumber() found: " + account);
        model.addAttribute("account", account);
        return "account";
    }

}
