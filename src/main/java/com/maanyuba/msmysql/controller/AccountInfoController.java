package com.maanyuba.msmysql.controller;

import com.maanyuba.msmysql.dto.AccountDto;
import com.maanyuba.msmysql.service.AccountInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@Slf4j
public class AccountInfoController {

    private final AccountInfoService accountInfoService;

    @RequestMapping(method = RequestMethod.POST, path = "/create", produces = "application/json")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        log.info("Start.AccountInfoController.createAccount");
        accountDto = accountInfoService.createAccountInfo(accountDto);
        if (accountDto != null) {
            log.info("End.AccountInfoController.createAccount");
            return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
        } else {
            log.error("End.AccountInfoController.createAccount");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
