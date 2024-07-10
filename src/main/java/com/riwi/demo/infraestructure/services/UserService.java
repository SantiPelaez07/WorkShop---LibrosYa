package com.riwi.demo.infraestructure.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.demo.api.request.UserRequest;
import com.riwi.demo.api.response.UserResponse;
import com.riwi.demo.domain.entities.UserEntity;
import com.riwi.demo.domain.repositories.UserRepository;
import com.riwi.demo.infraestructure.abstract_service.IUserService;
import com.riwi.demo.mappers.IUserMapper;
import com.riwi.demo.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public UserResponse create(UserRequest request) {
        UserEntity user = this.userRepository.save(this.userMapper.RequestToEntity(request));
        return this.userMapper.userToDto(user);
    }

    @Override
    public UserResponse getById(Long id) {
        UserResponse userResponse = this.userMapper.userToDto(this.userRepository.findById(id).orElseThrow());
        return userResponse;
    }

    @Override
    public UserResponse update(UserRequest request, Long id) {
        UserEntity user = this.findId(id);
        user = this.userMapper.RequestToEntity(request);
        user.setId(id);
        return this.userMapper.userToDto(this.userRepository.save(user));
    }

    @Override
    public void delete(Long id) {
        UserEntity user = this.findId(id);
        this.userRepository.delete(user);
    }

    @Override
    public Page<UserResponse> getAll(int page, int size, SortType sort) {
        if (page < 0) {
            page = 0;
        }

        PageRequest pagination = null;

        switch(sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.userRepository.findAll(pagination).map(this.userMapper::userToDto);
    }

    private UserEntity findId(Long id){
        return this.userRepository.findById(id).orElseThrow();
    }

}
