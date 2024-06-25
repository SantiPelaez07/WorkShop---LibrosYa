package com.riwi.demo.api.request;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {

    private LocalDate loan_date;
    private LocalDate return_date;
    private String status;
    private Long user;
    private Long book;
}
