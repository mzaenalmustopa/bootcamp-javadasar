package com.mznlmstpa_security.controller;

import com.mznlmstpa_security.config.AuthenticationFacade;
import com.mznlmstpa_security.config.JwtUtils;
import com.mznlmstpa_security.dto.AuthRequest;
import com.mznlmstpa_security.dto.Response;
import com.mznlmstpa_security.dto.UserResponse;
import com.mznlmstpa_security.entity.UserEntity;
import com.mznlmstpa_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final AuthenticationFacade authenticationFacade;

    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@RequestBody AuthRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        final UserDetails user = userService.loadUserByUsername(request.getEmail());
        if (user != null ){
            String token = jwtUtils.generateToken(user);
            return ResponseEntity.ok().body(new Response(200,"Success",token));
        }else {
            return ResponseEntity.status(400).body(new Response(400, "Something error has occurred", null));
        }
    }

    @GetMapping("/username")
    public ResponseEntity<Response> getUsername(){
        Authentication authentication = authenticationFacade.getAuthentication();
        String userName = authentication.getName();
        Optional<UserEntity> userEntity = userService.getByEmail(userName);

        if (userEntity.isPresent()){
            UserResponse result = new UserResponse(userEntity.get());
            return ResponseEntity.ok().body(new Response(200, "Success", result));
        }else {
            return ResponseEntity.status(500).body(new Response(500, "Failed","User Not Found"));
        }
    }

}
