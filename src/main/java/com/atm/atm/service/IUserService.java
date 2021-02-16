package com.atm.atm.service;

import com.atm.atm.dto.AccountDto;
import com.atm.atm.dto.BalanceEnquiryDTO;
import com.atm.atm.model.Account;

public interface IUserService {

    void createUserAccount(Account account);

    BalanceEnquiryDTO getUserAccount(String accountNumber, String ifscCode);

    void getCashDeposit(String accountNumber, String ifscCode, Integer deposit);

    void getCashWithdrawl(String accountNumber, String ifscCode, Integer withdrawl,String atmPin);


}
