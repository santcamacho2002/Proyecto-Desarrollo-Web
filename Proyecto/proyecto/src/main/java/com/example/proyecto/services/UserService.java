package com.example.proyecto.services;

import com.example.proyecto.entities.User;
import com.example.proyecto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void newUser(User user){
        userRepository.save(user);
    }

    public void updateUser(User user , Long id){
        Optional<User> UsuarioExistente = userRepository.findById(id);
        if (UsuarioExistente.isPresent()) {
            User updatedUser = UsuarioExistente.get();
            updatedUser.setUserName(user.getUserName());
            updatedUser.setPswd(user.getPswd());
            userRepository.save(updatedUser);
        } else {
            System.out.println("No existe la herramienta");
        }
    }

    public void deleteUser(Long id){
        Optional<User> usuarioExistente = userRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            userRepository.delete(usuarioExistente.get());
        } else {
            System.out.println("No existe la herramienta");
        }
    }
}
