package com.riwi.demo.api.response;

import java.time.LocalDate;

import com.riwi.demo.domain.entities.Book;
import com.riwi.demo.domain.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanResponse {

    private Long id;
    private LocalDate loan_date;
    private LocalDate return_date;
    private String status;
    private UserEntity user;
    private Book book;
}
