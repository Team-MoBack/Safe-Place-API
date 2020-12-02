//package com.moBack.backend;
//
//import static org.junit.Assert.assertNotNull;
//
//import javax.transaction.Transactional;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ActiveProfiles;
//
//import com.moBack.backend.dao.UserRepository;
//import com.moBack.backend.entity.User;
//
//@ActiveProfiles("test")
//@Transactional
//public class UserJpaTest extends AbstractTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void user_save_test() {
//        final User user = new User("killdong","hong","test@gmail.com","1234");
//        final User savedUser = userRepository.save(user);
//        assertNotNull(savedUser.getId());
//    }
//
//    @Test
//    public void user_save_and_find_test() {
//    	final User user = new User("killdong","hong","test@gmail.com","1234");
//        final User savedUser = userRepository.save(user);
//        final User foundUser = userRepository.findByEmail(savedUser.getEmail()).get();
//        assertNotNull(foundUser.getId());
//    }
//    
//    
//}