package com.DeathTime.API.Spring.DeathTime.API.Spring.User.Repository;

import com.DeathTime.API.Spring.DeathTime.API.Spring.User.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    boolean existsByEmail(String email);

    boolean existsByName(String name);
}
