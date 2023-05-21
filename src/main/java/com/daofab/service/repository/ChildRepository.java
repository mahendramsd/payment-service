package com.daofab.service.repository;

import com.daofab.service.domain.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mahendra on 5/18/2023
 */
@Repository
public interface ChildRepository extends JpaRepository<Child, Integer> {
}
