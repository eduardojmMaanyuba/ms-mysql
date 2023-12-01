package com.maanyuba.msmysql.service;

import com.maanyuba.msmysql.dto.AccountDto;
import com.maanyuba.msmysql.entity.AccountInfo;
import com.maanyuba.msmysql.entity.User;
import com.maanyuba.msmysql.repository.AccountInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountInfoService {

    private final AccountInfoRepository accountInfoRepository;
    private final UserService userService;

    public AccountDto createAccountInfo(AccountDto accountDto) {
        try {
            User user = userService.getUserFromMail(accountDto.getMail());
            AccountInfo accountInfo = AccountInfo.builder()
                    .accountBalance(accountDto.getAccountBalance())
                    .accountNumber(accountDto.getAccountNumber())
                    .accountType(accountDto.getAccountType())
                    .user(user)
                    .build();
            accountInfo = accountInfoRepository.save(accountInfo);
            accountDto.setId(accountInfo.getId());
            return accountDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            return null;
        }
    }
}
