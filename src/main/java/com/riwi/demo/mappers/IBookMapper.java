package com.riwi.demo.mappers;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import com.riwi.demo.domain.entities.Book;
import com.riwi.demo.domain.entities.Loan;
import com.riwi.demo.domain.entities.Reservation;
import com.riwi.demo.api.response.BookResponse;
import com.riwi.demo.api.request.BookRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IBookMapper {

    @Mapping(source = "reservation", target = "reservation", qualifiedByName = "mapIdToReservation")
    @Mapping(source = "loans", target = "loans", qualifiedByName = "mapIdToLoans")
    @Mapping(target = "id", ignore = true)
    Book requestToEntity(BookRequest book);

    // @Mapping(target = "reservation", ignore = true)
    BookResponse entityToResponse(Book book);

    Book responseToEntity(BookResponse book);

    @Named("mapIdToLoans")
    default List<Loan> mapIdToLoans(Long id) {
        if (id == null) return new ArrayList<>();
            Loan loans = new Loan();
            loans.setId(id);
            List<Loan> loanList = new ArrayList<>();
            loanList.add(loans);
            return loanList;
    }

    @Named("mapIdToReservation")
    default List<Reservation> mapIdToReservation(Long id) {
        if (id != null) {
            Reservation reservation = new Reservation();
            reservation.setId(id);
            List<Reservation> reservations = new ArrayList<>();
            reservations.add(reservation);
            return reservations;
        }
        return new ArrayList<>();
    }
}
