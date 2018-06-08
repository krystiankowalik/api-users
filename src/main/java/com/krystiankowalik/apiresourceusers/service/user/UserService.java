package com.krystiankowalik.apiresourceusers.service.user;

import com.krystiankowalik.apiresourceusers.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserEntity> getAllUsers();

    Optional<UserEntity> getUser(String username);

    void updateUser(UserEntity userEntity);

    void saveUser(UserEntity userEntity);

    void deleteUser(UserEntity userEntity);
}
