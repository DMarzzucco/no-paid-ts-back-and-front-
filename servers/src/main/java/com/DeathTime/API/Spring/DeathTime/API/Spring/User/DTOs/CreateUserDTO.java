package com.DeathTime.API.Spring.DeathTime.API.Spring.User.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "The name should not be more than 20 character")
    private String name;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Age is required")
    private String age;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String name, String email, String age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public @NotBlank(message = "Name is required") @Size(max = 50, message = "The name should not be more than 20 character") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") @Size(max = 50, message = "The name should not be more than 20 character") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Age is required") String getAge() {
        return age;
    }

    public void setAge(@NotBlank(message = "Age is required") String age) {
        this.age = age;
    }
}
