package com.atm.atm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceEnquiryDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String bankName;
    private Double balance;
    private String accountNumber;
    private String ifscCode;
}
