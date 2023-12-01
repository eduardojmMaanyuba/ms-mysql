package com.maanyuba.msmysql.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDto {

    private int id;
    private String accountNumber;
    private String accountType;
    private String accountBalance;
    private String mail;
}
