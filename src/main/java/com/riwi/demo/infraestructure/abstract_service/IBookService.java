package com.riwi.demo.infraestructure.abstract_service;

import com.riwi.demo.api.request.BookRequest;
import com.riwi.demo.api.response.BookResponse;

public interface IBookService extends CrudDefault<BookRequest, BookResponse, Long>{
    public final String FIELD_BY_SORT = "title";
}
