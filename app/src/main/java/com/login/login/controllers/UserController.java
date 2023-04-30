package com.login.login.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.login.login.entities.User;
import com.login.login.services.UserService;
import com.login.login.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.websocket.server.PathParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody User user){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1, user.getPassword());
        user.setPassword(hash);
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")//En este caso se agrega una autenticacion para probar el metodo, solo el usuario logueado podria borrarse a si mismo
    public ResponseEntity<?> delete(@PathVariable int id, @RequestHeader(value = "Authorization") String token){

        Boolean authorization = JWTUtil.validateToken(token);
        if(!authorization){
            return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
        }
        Optional<User> user = userService.findById(id);
        if( !user.isPresent() ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.delUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping(value = "/get")
    public ResponseEntity<?> get(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }
}
