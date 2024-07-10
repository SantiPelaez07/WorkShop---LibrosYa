package com.riwi.demo.infraestructure.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.demo.api.request.ReservationRequest;
import com.riwi.demo.api.response.ReservationResponse;
import com.riwi.demo.domain.entities.Book;
import com.riwi.demo.domain.entities.Reservation;
import com.riwi.demo.domain.repositories.ReservationRepository;
import com.riwi.demo.infraestructure.abstract_service.IBookService;
import com.riwi.demo.infraestructure.abstract_service.IReservationService;
import com.riwi.demo.mappers.IBookMapper;
import com.riwi.demo.mappers.IReservationMapper;
import com.riwi.demo.utils.enums.SortType;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ReservationService implements IReservationService{
    
    private final ReservationRepository reservationRepository;
    private final IReservationMapper reservationMapper;
    private final IBookService bookService;
    private final IBookMapper bookMapper;
    
    
    @Override
    public ReservationResponse create(ReservationRequest request) {
        Reservation reservation = this.reservationMapper.requestToEntity(request);
        Book getBook = this.bookMapper.responseToEntity(this.bookService.getById(request.getBooks()));
        reservation.setBooks(getBook); 
        reservation.setUser(this.reservationMapper.mapIdToEntity(request.getUser()));
        return this.reservationMapper.entityToResponse(this.reservationRepository.save(reservation));
    }

    @Override
    public ReservationResponse getById(Long id) {
        return this.reservationMapper.entityToResponse(this.findId(id));
    }

    @Override
    public ReservationResponse update(ReservationRequest request, Long id) {
        Reservation reservation = this.findId(id);
        reservation = this.reservationMapper.requestToEntity(request);
        reservation.setId(id);
        // reservation.setBooks(this.reservationMapper.mapIdToList(request.getBooks()));
        reservation.setUser(this.reservationMapper.mapIdToEntity(request.getUser()));
        return this.reservationMapper.entityToResponse(this.reservationRepository.save(reservation));
    }

    @Override
    public void delete(Long id) {
        Reservation reservation = this.findId(id);
        this.reservationRepository.delete(reservation);
    }

    @Override
    public Page<ReservationResponse> getAll(int page, int size, SortType sort) {
        if (page < 0) {
            page = 0;
        }

        PageRequest pagination = null;
        switch(sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.reservationRepository.findAll(pagination).map(this.reservationMapper::entityToResponse);
    }

    private Reservation findId(Long id){
        Reservation reservation = this.reservationRepository.findById(id).orElseThrow();
        return reservation;
    }

}
