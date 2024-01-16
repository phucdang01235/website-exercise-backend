package com.example.mywebsite.services.user;

import com.example.mywebsite.config.JwtTokenUtil;
import com.example.mywebsite.dtos.UserDTO;
import com.example.mywebsite.entity.User;
import com.example.mywebsite.entity.Role;
import com.example.mywebsite.exceptions.DataNotFoundException;
import com.example.mywebsite.exceptions.PermissionDenyException;
import com.example.mywebsite.repo.RoleRepo;
import com.example.mywebsite.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        // Check if email exists or not
        String email = userDTO.getEmail();
        if (userRepo.existsByEmail( email )){
            throw new DataIntegrityViolationException("User already exists");
        }

        Role role = roleRepo.findById(userDTO.getRoleId())
                .orElseThrow(() ->
                    new DataNotFoundException("Role not found with id " + userDTO.getRoleId())
                );

        if(role.getName().toUpperCase().equals(Role.ADMIN)){
            throw new PermissionDenyException("You can not register a user administrator");
        }

        //Convert from UserDTO to User
        User newUser = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .salary(userDTO.getSalary())
                .age(userDTO.getAge())
                .dateOfBirth(userDTO.getDateOfBirth())
                .active(true)
                .role(role)
                .build();

        // Nếu ngươời dùng không có id facebook và google account thì sẽ mã hóa password để lưu vào REPO
        if (userDTO.getFacebookAccountId() == 0 && userDTO.getGoogleAccountId() == 0) {
            String password = userDTO.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            newUser.setPassword(encodedPassword);
        }

        return userRepo.save(newUser);
    }

    @Override
    public String login(String email, String password, Long roleId) throws Exception {
        // Check email
        Optional<User> optionalUser = userRepo.findByEmail(email);

        if( optionalUser.isEmpty() ){
            throw new DataNotFoundException("Not found user with " + email);
        }

        User existingUser = optionalUser.get();

        // Nếu ngươời dùng không có id facebook và google account thì sẽ giải mã password encoded để kiểm tra
        if (existingUser.getFacebookAccountId() == 0
                && existingUser.getGoogleAccountId() == 0) {
            // check password
            if(!passwordEncoder.matches(password, existingUser.getPassword())) {
                throw new BadCredentialsException("Wrong email or password.");
            }
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                email, password, existingUser.getAuthorities()
        );

        authenticationManager.authenticate(authenticationToken);

        return jwtTokenUtil.generateToken(existingUser);
    }


}
