package com.moBack.backend;

import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import com.moBack.backend.dao.UserRepository;
import com.moBack.backend.entity.User;

//@ActiveProfiles("test")
@Transactional
public class UserJpaTest extends AbstractTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTest() {
        final User user = new User("killdong","hong","test@gmail.com","1234");
        final User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
    }


    
    
}