package com.wechat.web;

import com.wechat.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wechat.account.AccountNotFoundException;

@Service
public class WebAccountsService {

    @Autowired        // NO LONGER auto-created by Spring Cloud (see below)
    @LoadBalanced     // Explicitly request the load-balanced template // with Ribbon built-in
    protected RestTemplate restTemplate;

    protected String serviceUrl;

    public WebAccountsService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ?
                serviceUrl : "http://" + serviceUrl;
    }

    public Account getByNumber(String accountNumber) throws AccountNotFoundException {
        Account account = restTemplate.getForObject(serviceUrl
                + "/accounts/{number}", Account.class, accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        } else
            return account;
    }
}
