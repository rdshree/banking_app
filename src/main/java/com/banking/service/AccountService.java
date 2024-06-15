package com.banking.service;

import com.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto account);

    AccountDto getAccouuntById(Long id);

    AccountDto deposit(Long id, Double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();
}
