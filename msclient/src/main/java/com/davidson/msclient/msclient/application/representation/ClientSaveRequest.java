package com.davidson.msclient.msclient.application.representation;

import com.davidson.msclient.msclient.domain.Client;
import lombok.Data;

@Data
public class ClientSaveRequest {

    private String cpf;
    private String name;
    private Integer age;

    public Client toModel() {
        return new Client(cpf,name,age);
    }

}
