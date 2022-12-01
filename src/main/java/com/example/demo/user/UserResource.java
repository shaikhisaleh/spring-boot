package com.example.demo.user;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {
    private UserDaoService service;
    public UserResource(UserDaoService service){
        this.service = service;
    }

    @GetMapping
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }
    @GetMapping(path = "/{id}")
    public User findUserById(@PathVariable int id){
        User user =  service.findOne(id);
        if (user == null){
            throw new UserNotFoundException("id"+id);
        }
        return user;
    }
    @DeleteMapping(path = "/{id}")
    public void deleteUserById(@PathVariable int id){
        service.removeUserById(id);
    }
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser =service.addUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
