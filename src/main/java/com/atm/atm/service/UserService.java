package com.atm.atm.service;


import com.atm.atm.dto.BalanceEnquiryDTO;
import com.atm.atm.model.Account;
import com.atm.atm.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void createUserAccount(Account account) {
        accountRepository.save(account);
    }

    public BalanceEnquiryDTO getUserAccount(String accountNumber, String ifscCode) {
        logger.info("Fetching the details of user");
        if (accountNumber.isEmpty() || ifscCode.isEmpty()) {
            logger.warn("AccountNumber or ifscCode is empty");
            return null;
        }

        Account account = accountRepository.findByAccountNumberAndIfscCode(accountNumber, ifscCode);
        if (account == null) {
            return null;
        }
        return BalanceEnquiryDTO.builder()
                .id(account.getId())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .bankName(account.getBankName())
                .balance(account.getBalance())
                .balance(account.getBalance())
                .accountNumber(account.getAccountNumber())
                .ifscCode(account.getIfscCode())
                .build();
    }

    public void getCashDeposit(String accountNumber, String ifscCode, Integer deposit) {
        logger.info("Fetching the details of user");
        if (accountNumber.isEmpty() || ifscCode.isEmpty()) {
            logger.warn("AccountNumber or ifscCode is empty");
            return;
        }

        Account account = accountRepository.findByAccountNumberAndIfscCode(accountNumber, ifscCode);
        if (account == null) {
            return;
        }
        double balance = account.getBalance() + deposit;
        account.setBalance(balance);
        logger.info("Cash Deposit is successfull");
        accountRepository.save(account);
    }


    public void getCashWithdrawl(String accountNumber, String ifscCode, Integer withdrawl, String atmPin) {
        logger.info("Fetching the details of user");
        if (accountNumber.isEmpty() || ifscCode.isEmpty()) {
            logger.warn("AccountNumber or ifscCode is empty");
            return;
        }

        Account account = accountRepository.findByAccountNumberAndIfscCode(accountNumber, ifscCode);
        if (account == null || !account.getAtmPin().equals(atmPin) || withdrawl > account.getBalance()) {
            return;
        }

        double balance = account.getBalance() - withdrawl;
        account.setBalance(balance);
        logger.info("Cash withdrawl is successfull");
        accountRepository.save(account);
    }

}
