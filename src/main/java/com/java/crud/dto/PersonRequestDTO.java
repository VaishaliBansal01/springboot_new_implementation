package com.java.crud.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PersonRequestDTO {

    @Getter
    @Setter
    String email;

    @Getter
    @Setter
    String address;

    public PersonRequestDTO(String email, String address) {
        this.email = email;
        this.address = address;
    }
}
