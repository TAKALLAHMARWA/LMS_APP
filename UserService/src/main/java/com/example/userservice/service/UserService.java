package com.example.userservice.service;


import com.example.userservice.dao.Entity.UserEntity;
import com.example.userservice.dao.Repository.UserRepository;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.exeption.ResourceNotFoundException;
import com.example.userservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.toEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        return userMapper.toDTO(userEntity);
    }

    public UserDTO getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new ResourceNotFoundException("User not found with username: " + username);
        }
        return userMapper.toDTO(userEntity);
    }

    public void deleteUser(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        userRepository.delete(userEntity);
    }
}
