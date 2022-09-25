package com.anvar.sbc.repository;

import com.anvar.sbc.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    public  Long countById(Integer id);
}
