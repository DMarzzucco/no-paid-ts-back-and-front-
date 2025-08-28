package com.DeathTime.API.Spring.DeathTime.API.Spring.User.Service;

import com.DeathTime.API.Spring.DeathTime.API.Spring.User.DTOs.CreateUserDTO;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.DTOs.UpdateUserDTO;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Model.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    ResponseEntity<List<UserModel>> GetAll();

    ResponseEntity<UserModel> GetById(Long id);

    ResponseEntity<Object> Create(CreateUserDTO body);

    ResponseEntity<Boolean> Update(UpdateUserDTO body, Long id);

    ResponseEntity<Boolean> Delete(Long id);
}
