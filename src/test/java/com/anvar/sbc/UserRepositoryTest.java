package com.anvar.sbc;

import com.anvar.sbc.model.User;
import com.anvar.sbc.repository.UserRepository;

import org.assertj.core.api.Assertions;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

//    public UserRepositoryTest(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setEmail("r.ramilov@gmail.com");
        user.setPassword("1234567");
        user.setFirstName("Ramil");
        user.setLastName("Ramilov");
        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void ListAll() {
        Iterable<User> users = userRepository.findAll();

        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void updateUser() {
        Integer userId = 1;
        Optional<User> optionalUser = userRepository.findById(userId);

        User user = optionalUser.get();
        user.setPassword("1234567");
        userRepository.save(user);

        User updatedUser = userRepository.findById(userId).get();

        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("1234567");

    }

    @Test
    public void get() {
        Integer userId = 2;
        Optional<User> optionalUser = userRepository.findById(userId);

        Assertions.assertThat(optionalUser).isPresent();

        System.out.println(optionalUser.get());


    }

    @Test
    public  void testDeleteUser(){
        Integer userId = 4;

        userRepository.deleteById(userId);

        Optional<User> optionalUser = userRepository.findById(userId);

        Assertions.assertThat(optionalUser).isNotPresent();
    }

}
