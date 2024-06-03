package com.saras.etl.controller;

import com.saras.etl.entity.Userinfo;
import com.saras.etl.model.AuthRequest;
import com.saras.etl.model.AuthResponse;
import com.saras.etl.security.JwtService;
import com.saras.etl.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/signup")
    public AuthResponse addNewUser(@RequestBody Userinfo user) {
        AuthResponse authResponse = new AuthResponse();
        Userinfo userinfo = service.addUser(user);
        if(userinfo != null){
            authResponse.setName(userinfo.getName());
            authResponse.setEmail(userinfo.getEmail());
            authResponse.setToken(jwtService.generateToken(userinfo.getEmail()));
            authResponse.setMessage("User Added Successfully");
        } else {
            authResponse.setMessage("Error While creating user");
        }
        return authResponse;
    }

    @PostMapping("/signin")
    public AuthResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        AuthResponse authResponse = new AuthResponse();
        if (authentication.isAuthenticated()) {
            authResponse.setEmail(authResponse.getEmail());
            authResponse.setToken(jwtService.generateToken(authRequest.getEmail()));
            if(authResponse.getToken() != null){
                authResponse.setName(jwtService.extractUsername(authResponse.getToken()));
            }
        } else {
            authResponse.setMessage("invalid user request !");
            throw new UsernameNotFoundException("invalid user request !");
        }
        return authResponse;
    }

    @GetMapping("/user/userProfile")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String userProfile() {
        return "Welcome to User Profile";
    }

    @GetMapping("/admin/adminProfile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }



}

