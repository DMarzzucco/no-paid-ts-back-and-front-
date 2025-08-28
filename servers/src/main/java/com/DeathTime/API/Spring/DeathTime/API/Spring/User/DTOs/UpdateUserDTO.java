package com.DeathTime.API.Spring.DeathTime.API.Spring.User.DTOs;

import jakarta.validation.constraints.Size;

public class UpdateUserDTO {

    @Size(max = 50, message = "Username should not be more than 50 characters")
    private String name;

    private String email;

    private String age;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(String name, String email, String age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public @Size(max = 50, message = "Username should not be more than 50 characters") String getName() {
        return name;
    }

    public void setName(@Size(max = 50, message = "Username should not be more than 50 characters") String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
