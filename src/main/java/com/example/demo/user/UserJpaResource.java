package com.example.demo.user;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.example.demo.resfulwebservices.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jpa/users")
public class UserJpaResource {
    private UserDaoService service;
    private UserRepository repository;
    public UserJpaResource(UserDaoService service, UserRepository repository){
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<User> retrieveAllUsers(){
        return repository.findAll();
    }

    @GetMapping(path = "/{id}")
    public User findUserById(@PathVariable int id){
        Optional<User> user =  repository.findById(id);
        if (user.isEmpty()){
            throw new UserNotFoundException("id"+id);
        }
        return user.get();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUserById(@PathVariable int id){
        repository.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser =repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
