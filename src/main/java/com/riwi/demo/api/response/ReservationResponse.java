package com.riwi.demo.api.response;

import java.time.LocalDate;
import java.util.List;

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
public class ReservationResponse {

    private Long id;
    private LocalDate date;
    private String status;
    private List<Book> books;
    private UserEntity user;
}
