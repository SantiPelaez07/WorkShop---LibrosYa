package com.riwi.demo.api.controllers;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.demo.api.response.ReservationResponse;
import com.riwi.demo.domain.entities.Reservation;
import com.riwi.demo.infraestructure.abstract_service.IReservationService;
import com.riwi.demo.utils.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/reservation")
public class ReservationController {
    private final IReservationService reservationService;

    @GetMapping
    public ResponseEntity<Page<ReservationResponse>> getAll(@RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestHeader(required = false) SortType sortType){
        if (Objects.isNull(sortType)) sortType = SortType.NONE;
        
        return ResponseEntity.ok(this.reservationService.getAll(page -1, size, sortType));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ReservationResponse> update(@PathVariable Long id){
        return ResponseEntity.ok(this.reservationService.getById(id));
    }

    @PostMapping
    



}   
