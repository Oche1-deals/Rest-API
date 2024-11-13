package com.oche.rest.webservices.restful_web_service.user;

import com.oche.rest.webservices.restful_web_service.jpa.PostRepository;
import com.oche.rest.webservices.restful_web_service.jpa.UserRepository;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    private UserRepository repository;
    private PostRepository postRepository;
    public UserJpaResource(UserRepository repository, PostRepository postRepository){
        this.repository = repository;
        this.postRepository = postRepository;
    }
    @GetMapping("/jpa/users")
    public List<User> reteriveAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> reteriveUsers(@PathVariable int id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id :"+id);
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).reteriveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;

    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUsers(@PathVariable int id){
        repository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrivePostForUser(@PathVariable int id){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id :"+id);

       return user.get().getPost();
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser( @Valid @RequestBody User user){
        User savedUser = repository.save(user);
        URI locatoin = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(locatoin).build();
    }
    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post){
        Optional<User> user = repository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException("id :"+id);

        post.setUser(user.get());

       Post savedPost = postRepository.save(post);

        URI locatoin = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(locatoin).build();

    }
}
