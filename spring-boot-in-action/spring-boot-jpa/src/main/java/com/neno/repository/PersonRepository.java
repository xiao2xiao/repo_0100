package com.neno.repository;

import com.neno.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: root
 * @Date: 2019/3/26 23:39
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Object> {
}
