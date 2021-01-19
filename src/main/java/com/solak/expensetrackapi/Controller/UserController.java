package com.solak.expensetrackapi.Controller;

import com.solak.expensetrackapi.Constants.Constants;
import com.solak.expensetrackapi.Exception.EtAuthException;
import com.solak.expensetrackapi.Model.Et_Users;
import com.solak.expensetrackapi.Service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login/{email}/{password}")
    public ResponseEntity<Map<String, String>> loginUser(@PathVariable String email, @PathVariable String password) throws EtAuthException {

        Et_Users user = userService.validateUser(email, password);
        return ResponseEntity.ok().body(generateJWTToken(user));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody Et_Users etUsers) throws EtAuthException {
        Et_Users user = userService.registerUser(etUsers);
        return ResponseEntity.ok().body(generateJWTToken(user));

    }

    private Map<String, String> generateJWTToken(Et_Users user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstname", user.getFirstName())
                .claim("lastName", user.getLastName())
                .compact();

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);

        return tokenMap;
    }
}
