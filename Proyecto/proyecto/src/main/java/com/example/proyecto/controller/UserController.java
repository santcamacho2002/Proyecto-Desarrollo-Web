package com.example.proyecto.controller;

import com.example.proyecto.entities.User;
import com.example.proyecto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.*;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestParam("user")String username, @RequestParam("password")String password){
        String token = getJWTToken(username);
            User user = new User();
            user.setUserName(username);
            user.setPswd(password);
            user.setToken(token);

            return user;
    }

    //CRUD Services
    @GetMapping(value = "/allUsers",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<User> userList(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping(value = "/newUser",produces = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public void createUser(@RequestBody User user) {
        userService.newUser(user);
    }

    @PutMapping(value = "/updateUser/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public void updateUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
        userService.updateUser(user,id);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable(value="id")Long id){
        userService.deleteUser(id);
    }

    private String getJWTToken(String username){x
        String secretKey = "secretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts.builder().setId("javerinaJWT").setSubject(username).
                claim("authorities",grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())).
                setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis()+600000)).
                signWith(SignatureAlgorithm.HS512,secretKey.getBytes()).compact();

        return "Bearer"+token;
    }

}
