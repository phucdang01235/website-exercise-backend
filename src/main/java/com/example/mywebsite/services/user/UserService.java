package com.example.mywebsite.services.user;

import com.example.mywebsite.dtos.UserDTO;
import com.example.mywebsite.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User createUser(UserDTO userDTO) throws Exception;
    String login(String email, String password, Long roleId) throws Exception;
//    User getUserDetailsFromToken(String token) throws Exception;
//    User getUserDetailsFromRefreshToken(String token) throws Exception;
//    User updateUser(Long userId, UpdateUserDTO updatedUserDTO) throws Exception;
//
//    Page<User> findAll(String keyword, Pageable pageable) throws Exception;
//    void resetPassword(Long userId, String newPassword)
//            throws InvalidPasswordException, DataNotFoundException ;
//    public void blockOrEnable(Long userId, Boolean active) throws DataNotFoundException;
}
