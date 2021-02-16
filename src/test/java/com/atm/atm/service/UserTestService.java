package com.atm.atm.service;

import com.atm.atm.controller.UserController;
import com.atm.atm.dto.AccountDto;
import com.atm.atm.dto.BalanceEnquiryDTO;
import com.atm.atm.model.Account;
import com.atm.atm.repository.AccountRepository;
import com.atm.atm.service.UserService;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserTestService {

    @Mock
    UserController sut;

    @InjectMocks
    UserService userService;

    @Mock
    AccountRepository accountRepository;

    private AccountDto accountDto;

    private MockMvc mockMvc;

    private BalanceEnquiryDTO balanceEnquiryDTO;

    Account account = new Account();
    String accountNumber = "12345678";
    String ifscCode = "OBC1234";
    Integer deposit = 1000;
    Integer withdrawl = 1000;
    String atmPin = "1234";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(sut).build();
        account.setFirstName("nancy");
        account.setLastName("Maheshwari");
        account.setBalance(250.00);
        account.setAccountNumber("12345678");
        account.setIfscCode("OBC1234");
        account.setEmailId("nan@gmail.com");
        account.setCurrentAddress("NIL");
        account.setPermanentAddress("Nil");
        account.setAtmPin("1234");
        account.setPhoneNumber("913342342");
    }

    @Test
    public void postcreateUserAccount_Return_WhenPassAttribute() throws Exception {
        this.accountRepository.save(account);
    }

    @Test
    public void getUserAccount_ReturnNull_WhenAccountNumberIsEmpty() throws Exception {
        BalanceEnquiryDTO balanceEnquiryDTO = userService.getUserAccount("", ifscCode);
        assertNull(balanceEnquiryDTO);
    }

    @Test
    public void getUserAccount_ReturnNull_WhenIfscCodeIsEmpty() throws Exception {
        BalanceEnquiryDTO balanceEnquiryDTO = userService.getUserAccount(accountNumber, "");
        assertNull(balanceEnquiryDTO);
    }

    @Test
    public void getUserAccount_ReturnResponse_WhenPassAttribute() throws Exception {
        when(this.accountRepository.findByAccountNumberAndIfscCode(accountNumber, ifscCode)).thenReturn(account);
        BalanceEnquiryDTO balanceEnquiryDTO = userService.getUserAccount(accountNumber, ifscCode);
        assertNotNull(balanceEnquiryDTO);
    }

    @Test
    public void getCashDeposit_Return_WhenAccountNumberIsEmpty() throws Exception {
        userService.getCashDeposit("", ifscCode, deposit);
    }

    @Test
    public void getCashDeposit_Return_WhenIfscCodeIsEmpty() throws Exception {
        userService.getCashDeposit(accountNumber, "", deposit);
    }

    @Test
    public void getCashDeposit_ReturnResponse_WhenPassAttribute() throws Exception {
        when(this.accountRepository.findByAccountNumberAndIfscCode(accountNumber, ifscCode)).thenReturn(account);
        userService.getCashDeposit(accountNumber, "", deposit);
    }

    @Test
    public void getCashWithdrawl_Return_WhenAccountNumberIsEmpty() throws Exception {
        userService.getCashWithdrawl("", ifscCode, withdrawl, atmPin);
    }

    @Test
    public void getCashWithdrawl_Return_WhenIfscCodeIsEmpty() throws Exception {
        userService.getCashWithdrawl(accountNumber, "", withdrawl, atmPin);
    }

    @Test
    public void getCashWithdrawl_Return_WhenAtmPinIsNotEqual() throws Exception {
        when(this.accountRepository.findByAccountNumberAndIfscCode(accountNumber, ifscCode)).thenReturn(account);
        userService.getCashWithdrawl(accountNumber, ifscCode, withdrawl, "123");
    }

    @Test
    public void getCashWithdrawl_Return_WhenWithdrawlIsMore() throws Exception {
        account.setBalance(100.00);
        when(this.accountRepository.findByAccountNumberAndIfscCode(accountNumber, ifscCode)).thenReturn(account);
        userService.getCashWithdrawl(accountNumber, ifscCode, withdrawl, atmPin);
    }

    @Test
    public void getCashWithdrawl_Return_WhenWithdrawlIsLess() throws Exception {
        account.setBalance(100000.00);
        when(this.accountRepository.findByAccountNumberAndIfscCode(accountNumber, ifscCode)).thenReturn(account);
        userService.getCashWithdrawl(accountNumber, ifscCode, withdrawl, atmPin);
    }
}
