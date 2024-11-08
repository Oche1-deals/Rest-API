package com.oche.rest.webservices.restful_web_service.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
public class UserResource {
    private UserDoaService service;
    public UserResource(UserDoaService service){
        this.service = service;
    }
    @GetMapping("/users")
    public List<User> reteriveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User reteriveUsers(@PathVariable int id){
        User user = service.findOne(id);
        if(user == null)
            throw new UserNotFoundException("id :"+id);
        return user;
    }
    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable int id){
        service.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser( @Valid @RequestBody User user){
        User savedUser = service.save(user);
        URI locatoin = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(locatoin).build();
    }
}
