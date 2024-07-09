package com.riwi.demo.infraestructure.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.demo.api.request.BookRequest;
import com.riwi.demo.api.response.BookResponse;
import com.riwi.demo.domain.entities.Book;
import com.riwi.demo.domain.repositories.BookRepository;
import com.riwi.demo.infraestructure.abstract_service.IBookService;
import com.riwi.demo.mappers.IBookMapper;
import com.riwi.demo.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final IBookMapper bookMapper;

    @Override
    public BookResponse create(BookRequest request) {
        Book book = this.bookRepository.save(this.bookMapper.requestToEntity(request));
        return this.bookMapper.entityToResponse(book);
    }

    @Override
    public BookResponse getById(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow();
        return this.bookMapper.entityToResponse(book);
    }

    @Override
    public BookResponse update(BookRequest request, Long id) {
        Book book = this.findBook(id);
        book = this.bookMapper.requestToEntity(request);
        book.setId(id);
        return this.bookMapper.entityToResponse(this.bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        Book book = this.findBook(id);
        this.bookRepository.delete(book);
    }

    @Override
    public Page<BookResponse> getAll(int page, int size, SortType sort) {
        if (page < 0) {
            page = 0;
        }

        PageRequest pagination = null;
        switch(sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.bookRepository.findAll(pagination).map(this.bookMapper::entityToResponse);
    }

    private Book findBook (Long id){
        return this.bookRepository.findById(id).orElseThrow();
    }

}
