package com.DeathTime.API.Spring.DeathTime.API.Spring.User.Controller;

import com.DeathTime.API.Spring.DeathTime.API.Spring.User.DTOs.CreateUserDTO;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.DTOs.UpdateUserDTO;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Model.UserModel;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/User")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping
    public ResponseEntity<List<UserModel>> GetAllUser() {
        return this.service.GetAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> GetUserById(@PathVariable Long id) {
        return this.service.GetById(id);
    }

    @PostMapping
    public ResponseEntity<Object> CreateUser(@RequestBody CreateUserDTO body) {
        return this.service.Create(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> UpdateUser(@RequestBody UpdateUserDTO body, @PathVariable Long id) {
        return this.service.Update(body, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> DeleteUser(@PathVariable Long id) {
        return this.service.Delete(id);
    }
}
