package com.udemy.rest.webservices.restfulwebservices.socialmedia.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class UserDaoService
{
  private static List<User> users     = new ArrayList<>();
  private static int        userCount = 0;

  static
  {
    users.add(new User(++userCount, "Adam", LocalDate.now()
                                                     .minusYears(30)));
    users.add(new User(++userCount, "Megan", LocalDate.now()
                                                      .minusYears(25)));
    users.add(new User(++userCount, "Jim", LocalDate.now()
                                                    .minusYears(44)));
  }

  public List<User> findAll()
  {
    return users;
  }

  public User findUser(Integer id)
  {
    return users.stream()
                .filter(u -> Objects.equals(u.getId(), id))
                .findFirst()
                .orElse(null);
  }

  public User save(User user)
  {
    user.setId(++userCount);
    users.add(user);
    return user;
  }

  public void removeUser(Integer id)
  {
    users.removeIf(u -> u.getId()
                         .equals(id));
  }
}
