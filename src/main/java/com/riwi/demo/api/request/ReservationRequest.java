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
public class ReservationRequest {

    private LocalDate date;
    private String status;
    private Long books;
    private Long user;
}
