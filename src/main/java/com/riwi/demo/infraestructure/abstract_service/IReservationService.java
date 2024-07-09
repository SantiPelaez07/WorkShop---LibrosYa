package com.riwi.demo.infraestructure.abstract_service;

import com.riwi.demo.api.request.ReservationRequest;
import com.riwi.demo.api.response.ReservationResponse;

public interface IReservationService extends CrudDefault <ReservationRequest, ReservationResponse, Long>{

    public final String FIELD_BY_SORT = "date";
}
