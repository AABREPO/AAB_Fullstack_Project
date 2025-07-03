package com.aabuilders.Dashboard.Service;

import com.aabuilders.Dashboard.DTO.UserDto;
import com.aabuilders.Dashboard.Entity.User;
import com.aabuilders.Dashboard.Entity.UserRoles;
import com.aabuilders.Dashboard.Repository.UserRepository;
import com.aabuilders.Dashboard.Repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    public void registerUser(UserDto userDto) throws Exception {
        String adminUsername = "Admin";
        String adminPassword = "AAbuilder@2025";
        if (!userDto.getAdminUsername().equals(adminUsername) || !userDto.getAdminPassword().equals(adminPassword)) {
            throw new Exception("Only admin can register new users");
        }
        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            throw new Exception("User with this email already exists");
        }
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserImage(userDto.getUserImage());
        user.setPosition(userDto.getPosition());
        userRepository.save(user);
    }
    public User updateUser(Long userId, UserDto userDto) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User not found with id: " + userId));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserImage(userDto.getUserImage());
        user.setPosition(userDto.getPosition());
        if (userDto.getUserRoles() != null) {
            List<UserRoles> rolesEntities = new ArrayList<>();
            for (UserRoles roleDto : userDto.getUserRoles()) {
                UserRoles existingRole = userRolesRepository.findByRoles(roleDto.getRoles());
                if (existingRole == null) {
                    existingRole = new UserRoles();
                    existingRole.setRoles(roleDto.getRoles());
                    userRolesRepository.save(existingRole);
                }
                rolesEntities.add(existingRole);
            }
            user.setUserRoles(rolesEntities);
        }
        return userRepository.save(user);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public void deleteUser(Long userId) throws Exception {
        if (!userRepository.existsById(userId)) {
            throw new Exception("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }
}
