package com.riwi.demo.infraestructure.abstract_service;

import com.riwi.demo.api.request.UserRequest;
import com.riwi.demo.api.response.UserResponse;

public interface IUserService extends CrudDefault<UserRequest, UserResponse, Long> {
    public final String FIEL_BY_SORT = "userName";
}
