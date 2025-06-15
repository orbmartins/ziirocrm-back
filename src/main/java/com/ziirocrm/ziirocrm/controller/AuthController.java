package com.ziirocrm.ziirocrm.controller;

import com.ziirocrm.ziirocrm.dto.AuthRequest;
import com.ziirocrm.ziirocrm.dto.AuthResponse;
import com.ziirocrm.ziirocrm.dto.RegisterRequest;
import com.ziirocrm.ziirocrm.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}