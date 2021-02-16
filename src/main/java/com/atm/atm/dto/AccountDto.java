package com.atm.atm.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String bankName;
    private Double balance;
    private String currentAddress;
    private String permanentAddress;
    private String phoneNumber;
    private String accountNumber;
    private String ifscCode;
}
