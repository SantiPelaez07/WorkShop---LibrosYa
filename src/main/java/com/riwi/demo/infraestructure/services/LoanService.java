package com.riwi.demo.infraestructure.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.demo.api.request.LoanRequest;
import com.riwi.demo.api.response.LoanResponse;
import com.riwi.demo.domain.entities.Loan;
import com.riwi.demo.domain.repositories.LoanRepository;
import com.riwi.demo.infraestructure.abstract_service.ILoanService;
import com.riwi.demo.mappers.ILoanMapper;
import com.riwi.demo.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanService implements ILoanService {
    
    private final LoanRepository loanRepository;
    private final ILoanMapper loanMapper;
    
    
    @Override
    public LoanResponse create(LoanRequest request) {
        Loan loan = this.loanMapper.requestToEntity(request);
        loan.setBook(this.loanMapper.requestToEntityBook(request.getBook()));
        loan.setUser(this.loanMapper.requestToEntityUser(request.getUser()));
        return this.loanMapper.entityToResponse(loan);
    }

    @Override
    public LoanResponse getById(Long id) {
        return this.loanMapper.entityToResponse(this.findId(id));
    }

    @Override
    public LoanResponse update(LoanRequest request, Long id) {
        Loan loan = this.findId(id);
        loan = this.loanMapper.requestToEntity(request);
        loan.setId(id);
        loan.setBook(this.loanMapper.requestToEntityBook(request.getBook()));
        loan.setUser(this.loanMapper.requestToEntityUser(request.getUser()));
        return this.loanMapper.entityToResponse(this.loanRepository.save(loan));
    }

    @Override
    public void delete(Long id) {
        Loan loan = this.findId(id);
        this.loanRepository.delete(loan);
    }

    @Override
    public Page<LoanResponse> getAll(int page, int size, SortType sort) {
        if (page < 0) {
            page = 0;
        }

        PageRequest pagination = null;
        switch(sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.loanRepository.findAll(pagination).map(this.loanMapper::entityToResponse);
    }


    private Loan findId(Long id){
        Loan loan = this.loanRepository.findById(id).orElseThrow();
        return loan;
    }

}
