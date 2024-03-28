package com.example.springangularreddit.security;

import com.example.springangularreddit.exceptions.SpringRedditException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import lombok.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;

@Service
public class JwtProvider {

    private KeyStore keyStore;
    @Value("${jwt.expiration.time}")
    private Long jwtExpirationInMillis;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new SpringRedditException("Exception occurred while loading keystore");
        }
    }

    public String generateToken(Authentication authentication) {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringRedditException("Exception occurred while retrieving public key from keystore");
        }
    }

    public boolean validateToken(String jwt) {
        parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            throw new SpringRedditException("Exception occurred while " +
                    "retrieving public key from keystore");
        }
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJwt(token)
                .getBody();

        return claims.getSubject();
    }

    public Long getJwtExpirationInMillis() {
        return jwtExpirationInMillis;
    }

}


//package com.example.springangularreddit.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//
//@Service
//@RequiredArgsConstructor
//public class JwtProvider {
//
//    private final JwtEncoder jwtEncoder;
//    @Value("${jwt.expiration.time}")
//    private Long jwtExpirationInMillis;
//
//    public String generateToken(Authentication authentication) {
//        User principal = (User) authentication.getPrincipal();
//        return generateTokenWithUserName(principal.getUsername());
//    }
//
//    public String generateTokenWithUserName(String username) {
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .issuer("self")
//                .issuedAt(Instant.now())
//                .expiresAt(Instant.now().plusMillis(jwtExpirationInMillis))
//                .subject(username)
//                .claim("scope", "ROLE_USER")
//                .build();
//
//        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
//    }
//
//    public Long getJwtExpirationInMillis() {
//        return jwtExpirationInMillis;
//    }
//
//}


//package com.example.springangularreddit.security;
//
//import com.example.springangularreddit.exceptions.SpringRedditException;
//import io.jsonwebtoken.Jwts;
//import jakarta.annotation.PostConstruct;
//import lombok.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.User;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.*;
//import java.security.cert.CertificateException;
//
//import static io.jsonwebtoken.Jwts.parser;
//
//@Service
//public class JwtProvider {
//
//    private KeyStore keyStore;
//
//    @PostConstruct
//    public void init() {
//        try {
//            keyStore = KeyStore.getInstance("JKS");
//            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
//            keyStore.load(resourceAsStream, "secret".toCharArray());
//        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
//            throw new SpringRedditException("Exception occurred while loading keystore");
//        }
//    }
//
//    public String generateToken(Authentication authentication) {
//        // This is the one I wrote:
//        org.springframework.security.core.userdetails.User principalLong = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
//        // This is the one he has (probably imported User separately):
//        org.springframework.security.core.userdetails.User principal = (User) authentication.getPrincipal();
//        return Jwts.builder()
//                .setSubject(principal.getUsername())
//                .signWith(getPrivateKey())
//                .compact();
//    }
//
//    private PrivateKey getPrivateKey() {
//        try {
//            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
//        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
//            throw new SpringRedditException("Exception occurred while retrieving public key from keystore");
//        }
//    }
//
//    public boolean validateToken(String jwt) {
//        parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
//        return true;
//    }
//
//    private PublicKey getPublicKey() {
//        try {
//            return keyStore.getCertificate("springblog").getPublicKey();
//        } catch (KeyStoreException e) {
//            throw new SpringRedditException("Exception occurred while " +
//                    "retrieving public key from keystore");
//        }
//    }
//
//}
//
//
////package com.example.springangularreddit.security;
////
////import lombok.RequiredArgsConstructor;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.security.core.Authentication;
////import org.springframework.security.core.userdetails.User;
////import org.springframework.security.oauth2.jwt.JwtClaimsSet;
////import org.springframework.security.oauth2.jwt.JwtEncoder;
////import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
////import org.springframework.stereotype.Service;
////
////import java.time.Instant;
////
////@Service
////@RequiredArgsConstructor
////public class JwtProvider {
////
////    private final JwtEncoder jwtEncoder;
////    @Value("${jwt.expiration.time}")
////    private Long jwtExpirationInMillis;
////
////    public String generateToken(Authentication authentication) {
////        User principal = (User) authentication.getPrincipal();
////        return generateTokenWithUserName(principal.getUsername());
////    }
////
////    public String generateTokenWithUserName(String username) {
////        JwtClaimsSet claims = JwtClaimsSet.builder()
////                .issuer("self")
////                .issuedAt(Instant.now())
////                .expiresAt(Instant.now().plusMillis(jwtExpirationInMillis))
////                .subject(username)
////                .claim("scope", "ROLE_USER")
////                .build();
////
////        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
////    }
////
////    public Long getJwtExpirationInMillis() {
////        return jwtExpirationInMillis;
////    }
////
////}
