package com.atm.atm.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "emailId")
    private String emailId;

    @Column(name = "bankName")
    private String bankName;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "currentAddress")
    private String currentAddress;

    @Column(name = "permanentAddress")
    private String permanentAddress;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "accountNumber")
    private String accountNumber;

    @Column(name = "ifscCode")
    private String ifscCode;

    @Column(name = "atmPin")
    private String atmPin;
}
