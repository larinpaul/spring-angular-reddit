package com.example.springangularreddit.controller;

import com.example.springangularreddit.dto.RegisterRequest;
import com.example.springangularreddit.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
    }

}


//package com.example.springangularreddit.controller;
//
//import com.example.springangularreddit.dto.AuthenticationResponse;
//import com.example.springangularreddit.dto.LoginRequest;
//import com.example.springangularreddit.dto.RefreshTokenRequest;
//import com.example.springangularreddit.dto.RegisterRequest;
//import com.example.springangularreddit.service.AuthService;
//import com.example.springangularreddit.service.RefreshTokenService;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import static org.springframework.http.HttpStatus.OK;
//
//@RestController
//@RequestMapping("/api/auth")
//@AllArgsConstructor
//public class AuthController {
//
//    private final AuthService authService;
//    private final RefreshTokenService refreshTokenService;
//
//    @PostMapping("/signup")
//    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
//        authService.signup(registerRequest);
//        return new ResponseEntity<>("User Registration Successful", OK);
//    }
//
//    @GetMapping("accountVerification/{token}")
//    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
//        authService.verifyAccount(token);
//        return new ResponseEntity<>("Account Activated Successfully", OK);
//    }
//
//    @PostMapping("/login")
//    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
//        return authService.login(loginRequest);
//    }
//
//    @PostMapping("/refresh/token")
//    public AuthenticationResponse refreshTokens(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
//        return authService.refreshToken(refreshTokenRequest);
//    }
//
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
//        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
//        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!");
//    }
//
//}
