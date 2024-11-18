package com.zekiyetekin.surveyhub.repository;

import com.zekiyetekin.surveyhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByMail(String mail);
}
