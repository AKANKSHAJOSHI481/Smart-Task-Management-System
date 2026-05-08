package com.stm.smart_task_management.auth;

import com.stm.smart_task_management.auth.dto.AuthResponse;
import com.stm.smart_task_management.auth.dto.LoginRequest;
import com.stm.smart_task_management.auth.dto.RegisterRequest;
import com.stm.smart_task_management.security.JwtService;
import com.stm.smart_task_management.user.User;
import com.stm.smart_task_management.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request){
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole("USER");
        userRepository.save(user);
        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.email()).orElseThrow();
        boolean matches = passwordEncoder.matches(
                request.password(),
                user.getPassword()
        );
        if(!matches){
            throw new RuntimeException("Invalid password or email");
        }
        String token =  jwtService.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
