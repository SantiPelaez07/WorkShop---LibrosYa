
package com.riwi.demo.infraestructure.abstract_service;

import com.riwi.demo.api.request.LoanRequest;
import com.riwi.demo.api.response.LoanResponse;

public interface ILoanService extends CrudDefault <LoanRequest, LoanResponse, Long>{

    public final String FIELD_BY_SORT = "loan_date";
}
