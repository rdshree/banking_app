package com.banking.service.impl;

import com.banking.dto.AccountDto;
import com.banking.entity.Account;
import com.banking.mapper.AccountMapper;
import com.banking.respository.AccountRepository;
import com.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccouuntById(Long id) {
        Account account = accountRepository
                .findById(id).
                orElseThrow(() -> new RuntimeException("Account doest not exists"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, Double amount) {

        Account account = accountRepository
                .findById(id).
                orElseThrow(() -> new RuntimeException("Account doest not exists"));

        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {


        Account account = accountRepository
                .findById(id).
                orElseThrow(() -> new RuntimeException("Account doest not exists"));
        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient account");
        }
         double total = account.getBalance()- amount;
         account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        accountRepository.findAll();
        List<Account>accounts = accountRepository.findAll();
        return  accounts.stream().map((account -> AccountMapper.mapToAccountDto(account)))
                .collect(Collectors.toList());



}
}





















