package com.atm.atm.controller;

import com.atm.atm.dto.BalanceEnquiryDTO;
import com.atm.atm.model.Account;
import com.atm.atm.service.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createUserAccount(@RequestBody Account account) {
        logger.info("User Account created");
        userService.createUserAccount(account);
    }

    @GetMapping(value = "/user/account/{accountNumber}/ifsc/{ifscCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BalanceEnquiryDTO> getUserBalance(@PathVariable("accountNumber") String accountNumber, @PathVariable("ifscCode") String ifscCode) {
        if (accountNumber == null || ifscCode == null) {
            logger.warn("AccountNumber or ifscCode is null");
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

        BalanceEnquiryDTO userAccount = userService.getUserAccount(accountNumber, ifscCode);
        if (userAccount == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userAccount, HttpStatus.OK);
    }

    @PutMapping(value = "/user/account/{accountNumber}/ifsc/{ifscCode}/Deposit/{deposit}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getCashDepositInUserAccount(@RequestBody Account account, @PathVariable("accountNumber") String accountNumber, @PathVariable("ifscCode") String ifscCode, @PathVariable("deposit") Integer deposit) {
        userService.getCashDeposit(accountNumber, ifscCode, deposit);
    }

    @PutMapping(value = "/user/account/{accountNumber}/ifsc/{ifscCode}/Withdrawl/{withdrawl}/atmPin/{atmPin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getCashWithdrawlFromUserAccount(@RequestBody Account account, @PathVariable("accountNumber") String accountNumber, @PathVariable("ifscCode") String ifscCode, @PathVariable("withdrawl") Integer withdrawl, @PathVariable("atmPin") String atmPin) {
        userService.getCashWithdrawl(accountNumber, ifscCode, withdrawl, atmPin);
    }

}
