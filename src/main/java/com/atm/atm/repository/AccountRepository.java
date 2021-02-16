package com.atm.atm.repository;

import com.atm.atm.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNumberAndIfscCode(String accountNumber, String ifscCode);

}
