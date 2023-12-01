package com.maanyuba.msmysql.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GenericResponse {

    private boolean status;
    private Object data;
    private String bussinessMeaning;
}
