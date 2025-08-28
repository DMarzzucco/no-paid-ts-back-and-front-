package com.DeathTime.API.Spring.DeathTime.API.Spring.User.Service.Impl;

import com.DeathTime.API.Spring.DeathTime.API.Spring.Mapper.ProfileMapper;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.DTOs.CreateUserDTO;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.DTOs.UpdateUserDTO;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Model.UserModel;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Repository.UserRepository;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private UserRepository repository;
    private ProfileMapper mapper;

    @Autowired
    public UserService(UserRepository repository, ProfileMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<List<UserModel>> GetAll() {
        List<UserModel> data = this.repository.findAll();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserModel> GetById(Long id) {
        UserModel user = this.repository.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> Create(CreateUserDTO body) {
        if (this.repository.existsByName(body.getName())) {
            return new ResponseEntity<>("This name already exist", HttpStatus.CONFLICT);
        }
        if (this.repository.existsByEmail(body.getName())) {
            return new ResponseEntity<>("This email already exist", HttpStatus.CONFLICT);
        }
        UserModel user = this.mapper.createUseDTOModel(body);
        this.repository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Boolean> Update(UpdateUserDTO body, Long id) {
        UserModel user = this.repository.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        this.mapper.updateUserDTOModel(body, user);
        this.repository.save(user);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Boolean> Delete(Long id) {
        UserModel user = this.repository.findById(id).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }
}
