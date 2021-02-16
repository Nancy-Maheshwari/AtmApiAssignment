package com.atm.atm.controller;

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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserTestController {

    @InjectMocks
    UserController sut;

    @MockBean
    UserService userService;

    @Mock
    AccountRepository accountRepository;

    private AccountDto accountDto;

    private MockMvc mockMvc;

    private BalanceEnquiryDTO balanceEnquiryDTO;

    private static final String post_Create_UserAccount = "/api/v1/user";
    private static final String get_UserBalance = "/api/v1/user/account/{accountNumber}/ifsc/{ifscCode}";
    private static final String get_CashDepositInUserAccount = "/api/v1/user/account/{accountNumber}/ifsc/{ifscCode}/Deposit/{deposit}";
    private static final String get_CashWithdrawlFromUserAccount = "/api/v1/user/account/{accountNumber}/ifsc/{ifscCode}/Withdrawl/{withdrawl}";
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
    public void postCreateUserAccount_Return_WhenPassAttribute() throws Exception {
        this.userService.createUserAccount(account);
        mockMvc.perform(post(post_Create_UserAccount, account)).andReturn();
    }

    @Test
    public void getUserBalance_Return200_WhenEverythingIsValid() throws Exception {
        BalanceEnquiryDTO balanceEnquiryDTO = new BalanceEnquiryDTO();
        when(this.userService.getUserAccount(accountNumber, ifscCode)).thenReturn(balanceEnquiryDTO);
        mockMvc.perform(get(get_UserBalance, accountNumber, ifscCode)).andExpect(status().isOk()).andReturn();
    }


    @Test
    public void getUserBalance_ReturnNotFound_WhenAccountNumberIsEmpty() throws Exception {
        when(this.userService.getUserAccount("", ifscCode)).thenReturn(null);
        mockMvc.perform(get(get_UserBalance, "", ifscCode)).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void getUserBalance_ReturnNotFound_WhenIfscCodeIsEmpty() throws Exception {
        when(this.userService.getUserAccount(accountNumber, "")).thenReturn(null);
        mockMvc.perform(get(get_UserBalance, accountNumber, "")).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void getCashDepositInUserAccount_Return_WhenPassAttribute() throws Exception {
        this.userService.getCashDeposit(accountNumber, ifscCode, deposit);
        mockMvc.perform(post(get_CashDepositInUserAccount, account, ifscCode, deposit)).andReturn();
    }

    @Test
    public void getCashWithdrawlFromUserAccount_Return_WhenPassAttribute() throws Exception {
        this.userService.getCashWithdrawl(accountNumber, ifscCode, withdrawl, atmPin);
        mockMvc.perform(post(get_CashDepositInUserAccount, account, ifscCode, deposit, atmPin)).andReturn();
    }
}
