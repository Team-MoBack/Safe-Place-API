package com.moBack.backend;

import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.moBack.backend.dao.AccountRepository;
import com.moBack.backend.entity.Account;

//@ActiveProfiles("test")
@Transactional
public class AccountJpaTest extends AbstractTest {

    @Autowired
    private AccountRepository accountRepository;

//    @Test
//    public void saveTest() {
//        final Account account = new Account("killdong","hong","test@gmail.com","1234");
//        final Account savedAccount = accountRepository.save(account);
//        assertNotNull(savedAccount);
//    }
}