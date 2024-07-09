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

    @Mapping(source = "reservation", target = "reservation", qualifiedByName = "mapIdToEntity")
    @Mapping(source = "loans", target = "loans", qualifiedByName = "mapIdToEntityList")
    @Mapping(target = "id", ignore = true)
    Book requestToEntity(BookRequest book);

    @Mapping(source = "reservation", target = "reservation", qualifiedByName = "mapIdToEntity")
    BookResponse entityToResponse(Book book);

    @Named("mapIdToEntityList")
    default List<Loan> mapIdToEntityList(Long id) {
        if (id != null) {
            Loan loans = new Loan();
            List<Loan> loanList = new ArrayList<>();
            loanList.add(loans);
            return loanList;
        }
        return new ArrayList<>();
    }

    @Named("mapIdToEntity")
    default List<Reservation> mapIdToEntity(Long id) {
        if (id != null) {
            Reservation reservation = new Reservation();
            List<Reservation> reservations = new ArrayList<>();
            reservation.setId(id);
            reservations.add(reservation);
            return reservations;
        }
        return new ArrayList<>();

    }
}
