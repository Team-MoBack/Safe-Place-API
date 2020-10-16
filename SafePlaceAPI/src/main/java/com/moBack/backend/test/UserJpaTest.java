package com.moBack.backend.test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.moBack.backend.entity.User;
import com.moBack.backend.util.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserJpaTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void user_save_test() {
        final User user = new User("killdong","hong","test@gmail.com","1234");
        final User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());
    }

    @Test
    public void user_save_and_find_test() {
    	final User user = new User("killdong","hong","test@gmail.com","1234");
        final User savedUser = userRepository.save(user);
        final User foundUser = userRepository.findByEmail(savedUser.getEmail()).get();
        assertNotNull(foundUser.getId());
    }
}