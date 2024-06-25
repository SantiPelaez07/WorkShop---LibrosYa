package com.riwi.demo.infraestructure.abstract_service;

import org.springframework.data.domain.Page;

import com.riwi.demo.enums.SortType;

public interface CrudDefault <RQ, RS, ID> {

    public RS create(RQ request);
    public RS getById(ID id);
    public RS update(RQ request, ID id);
    public void delete(ID id);
    public Page<RS> getAll(int page, int size, SortType sort);
}
