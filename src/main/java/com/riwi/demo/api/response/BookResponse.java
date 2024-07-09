package com.riwi.demo.api.response;

import java.util.List;

import com.riwi.demo.domain.entities.Loan;
import com.riwi.demo.domain.entities.Reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private int publication_year;
    private String genre;
    private String isbn;
    private List<Reservation> reservation;
    private List<Loan> loans;
}
