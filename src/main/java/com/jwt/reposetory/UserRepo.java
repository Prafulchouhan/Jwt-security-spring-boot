package com.jwt.reposetory;

import com.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUserName(String username);
}
