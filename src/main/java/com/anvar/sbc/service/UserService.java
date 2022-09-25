package com.anvar.sbc.service;

import com.anvar.sbc.exceptions.UserNotFoundException;
import com.anvar.sbc.model.User;
import com.anvar.sbc.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User getUser(Integer id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else throw new UserNotFoundException("No such user, with id " + id);
    }
    public void delete(Integer id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if(count == null || count == 0){
            throw  new UserNotFoundException("Could not find user with ID: " + id);

    }
        userRepository.deleteById(id);
    }
}
