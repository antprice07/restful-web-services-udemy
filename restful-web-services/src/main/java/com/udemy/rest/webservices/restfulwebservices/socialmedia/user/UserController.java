package com.udemy.rest.webservices.restfulwebservices.socialmedia.user;

import jakarta.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController
{

  private UserDaoService service;

  public UserController(UserDaoService service)
  {
    this.service = service;
  }

  @GetMapping("/users")
  public List<User> retrieveAllUsers()
  {
    return service.findAll();
  }

  @GetMapping("/users/{id}")
  public EntityModel<User> retrieveUser(@PathVariable Integer id)
  {
    User user = service.findUser(id);
    if (user == null)
    {
      throw new UserNotFoundException("id:" + id);
    }
    EntityModel<User> entityModel = EntityModel.of(user);
    WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
    entityModel.add(link.withRel("all-users"));
    return entityModel;
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Integer id)
  {
    service.removeUser(id);
  }

  @PostMapping("/users")
  public User addUser(@Valid @RequestBody User user)
  {
    return service.save(user);
  }
}
