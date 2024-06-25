package com.riwi.demo.api.response;

import java.util.List;

import com.riwi.demo.domain.entities.Loan;
import com.riwi.demo.domain.entities.Reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String userName;
    private String email;
    private String fullName;
    private String role;
    private List<Reservation> reservations;
    private List<Loan> loans;
}
