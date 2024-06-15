package com.banking.controller;

import com.banking.dto.AccountDto;
import com.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //add account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody  AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    //get account
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto>getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccouuntById(id);
        return ResponseEntity.ok(accountDto);
    }
    //Deposit
    @PutMapping("/{id}/deposit")
    public  ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request){

        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);

    }

    //Withdraw
    @PutMapping("{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request){

        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }
    //Get All Account

    @GetMapping
    public  ResponseEntity<List<AccountDto>> getAllAccount(){
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

}

















