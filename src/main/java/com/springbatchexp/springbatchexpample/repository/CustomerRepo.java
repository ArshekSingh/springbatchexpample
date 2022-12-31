package com.springbatchexp.springbatchexpample.repository;

import com.springbatchexp.springbatchexpample.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customers, Integer> {
}
