package com.riwi.demo.api.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private String title;
    private String author;
    private int publication_year;
    private String genre;
    private String isbn;
    private Long reservation;
    private Long loans;
}
