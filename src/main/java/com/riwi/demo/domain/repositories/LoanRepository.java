package com.riwi.demo.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.demo.domain.entities.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

}
