package com.zekiyetekin.surveyhub.repository;

import com.zekiyetekin.surveyhub.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
}
