package com.zekiyetekin.surveyhub.repository;

import com.zekiyetekin.surveyhub.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {
}
