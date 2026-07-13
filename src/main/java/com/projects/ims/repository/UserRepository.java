package com.projects.ims.repository;

import com.projects.ims.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);
    Optional<User> findByContact(String contact);
    Optional<User> findByUserNameOrEmail(String username, String email);
    boolean existsByEmail(String email);
    boolean existsByContact(String contact);
    boolean existsByUserName(String userName);
}
