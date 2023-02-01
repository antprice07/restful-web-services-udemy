package com.udemy.rest.webservices.restfulwebservices.socialmedia.user;

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
@RequestMapping("/jpa")
public class UserJpaController
{
  private final UserRepository repo;
  private final PostRepository postRepo;

  public UserJpaController(UserRepository repo, PostRepository postRepo)
  {
    this.repo = repo;
    this.postRepo = postRepo;
  }

  @GetMapping("/users")
  public List<User> retrieveAllUsers()
  {
    return repo.findAll();
  }

  @GetMapping("/users/{id}")
  public EntityModel<User> retrieveUser(@PathVariable Integer id)
  {
    Optional<User> user = repo.findById(id);
    if (user.isEmpty())
    {
      throw new UserNotFoundException("id:" + id);
    }
    EntityModel<User> entityModel = EntityModel.of(user.get());
    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
    entityModel.add(link.withRel("all-users"));
    return entityModel;
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Integer id)
  {
    repo.deleteById(id);
    repo.flush();
  }

  @PostMapping("/users")
  public ResponseEntity<Object> addUser(@Valid @RequestBody User user)
  {
    User savedUser = repo.save(user);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }

  @GetMapping("/users/{id}/posts")
  public List<Post> retrievePostsForUser(@PathVariable int id)
  {
    Optional<User> user = repo.findById(id);
    if (user.isEmpty())
      throw new UserNotFoundException("id: "+id);

    return user.get().getPosts();
  }
  @PostMapping("/users/{id}/posts")
  public ResponseEntity<Object> createPostsForUser(@PathVariable int id, @Valid @RequestBody Post post)
  {
    Optional<User> user = repo.findById(id);
    if (user.isEmpty())
      throw new UserNotFoundException("id: "+id);

    post.setUser(user.get());
    Post savedPost = postRepo.save(post);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                              .path("/{id}")
                                              .buildAndExpand(savedPost.getId())
                                              .toUri();
    return ResponseEntity.created(location).build();
  }
}
