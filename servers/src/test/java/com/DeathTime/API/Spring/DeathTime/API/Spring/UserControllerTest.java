package com.DeathTime.API.Spring.DeathTime.API.Spring;

import com.DeathTime.API.Spring.DeathTime.API.Spring.Mapper.ProfileMapper;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Controller.UserController;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.DTOs.CreateUserDTO;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.DTOs.UpdateUserDTO;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Model.UserModel;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Repository.UserRepository;
import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Service.IUserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    private MockMvc mockMvc;
    private UserRepository repository;
    private ProfileMapper mapper;


    @Autowired
    public UserControllerTest(MockMvc mockMvc, UserRepository repository, ProfileMapper mapper) {
        this.mockMvc = mockMvc;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Mock
    private IUserService service;

    @InjectMocks
    private UserController userController;

    @Test
    public void GetAllUser() throws Exception {
        List<UserModel> user = this.repository.findAll();

        when(this.service.GetAll()).thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/User")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{'id':1,'email':'Person@gmail.com','name':'Person','age':'23'},{'id':3,'email':'Person4@gmail.com','name':'Person4','age':'23'}]"));
    }

    @Test
    public void GetUserById() throws Exception {
        Long userId = 1L;
        UserModel user = new UserModel(userId, "Person@gmail.com", "Person", "23");
        this.repository.save(user);
        when(this.service.GetById(userId)).thenReturn(new ResponseEntity<>(user, HttpStatus.OK));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/User/{id}", userId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(userId))
                .andExpect(jsonPath("$.name").value("Person"))
                .andExpect(jsonPath("$.email").value("Person@gmail.com"))
                .andExpect(jsonPath("$.age").value("23"));
    }

    @Test
    public void CreateUser() throws Exception {
        UserModel user = new UserModel(1, "jane@example.com", "Jane Doe", "23");

        when(this.service.Create(any(CreateUserDTO.class))).thenReturn(new ResponseEntity<>(user, HttpStatus.CREATED));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/User")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Jane Doe\",\"email\":\"jane@example.com\",\"age\":\"23\" }"))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.age").value(user.getAge()));
    }

    @Test
    public void UpdateUSer() throws Exception {

        Long userId = 3L;
        UpdateUserDTO updateUser = new UpdateUserDTO("Person4", "Person4@gmail.com", "23");
        UserModel user = new UserModel(userId, "Person4@gmail.com", "Person4", "23");

        this.repository.save(user);
        this.mapper.updateUserDTOModel(updateUser, user);

        when(this.service.Update(any(UpdateUserDTO.class), eq(userId))).thenReturn(new ResponseEntity<>(true, HttpStatus.NO_CONTENT));

        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/User/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Person4\",\"email\":\"Person4@gmail.com\", \"age\":\"23\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void DeleteUser() throws Exception {
        Long userId = 2L;
        UserModel user = new UserModel(userId, "Person5@gmail.com", "Person5", "23");
        this.repository.save(user);

        when(this.service.Delete(eq(userId))).thenReturn(new ResponseEntity<>(true, HttpStatus.NO_CONTENT));

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/User/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Person5\",\"email\":\"Person5@gmail.com\", \"age\":\"23\"}"))
                .andExpect(status().isNoContent());

    }
}
