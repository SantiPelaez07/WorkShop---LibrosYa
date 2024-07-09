package com.riwi.demo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import com.riwi.demo.api.request.LoanRequest;
import com.riwi.demo.api.response.LoanResponse;
import com.riwi.demo.domain.entities.Book;
import com.riwi.demo.domain.entities.Loan;
import com.riwi.demo.domain.entities.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ILoanMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "book", target = "book", qualifiedByName = "requestToEntityBook")
    @Mapping(source = "user", target = "user", qualifiedByName = "requestToEntityUser")
    Loan requestToEntity(LoanRequest request);
    LoanResponse entityToResponse(Loan loan);


    @Named("requestToEntityBook")
    default Book requestToEntityBook(Long id){
        if (id == null) return null;
        Book book = new Book();
        book.setId(id);
        return book;
    }
    @Named("requestToEntityUser")
    default UserEntity requestToEntityUser(Long id){
        if (id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }
}
