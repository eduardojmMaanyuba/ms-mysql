package com.maanyuba.msmysql.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDto {

    private int id;
    private String name;
    private String fatherLastName;
    private String motherLastName;
    private Date bornDate;
    private String mail;
    private String phone;
}
