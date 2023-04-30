package com.login.login.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.login.entities.User;
import com.login.login.repository.UserRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user){
        Optional<User> userExist = userRepository.findByEmail(user.getEmail());
        if( userExist.isPresent()){
            throw new IllegalArgumentException("El email ya esta registrado");
        }
        userRepository.save(user);
    }

    public void delUser(int id){
        userRepository.deleteById(id);
    }

    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByCredentials(String email, String password){
        Boolean verify = false;
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            verify = argon2.verify(user.get().getPassword(),password);
            if(verify)
                return user;
        }
        Optional<User> opt = Optional.ofNullable(null); 

        return opt;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
    
    
}
