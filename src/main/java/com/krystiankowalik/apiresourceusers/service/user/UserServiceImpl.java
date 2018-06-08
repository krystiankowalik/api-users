package com.krystiankowalik.apiresourceusers.service.user;

import com.krystiankowalik.apiresourceusers.model.UserEntity;
import com.krystiankowalik.apiresourceusers.model.db.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;


    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUser(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public void updateUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        userRepository.delete(userEntity);
    }


}
