package com.udemy.rest.webservices.restfulwebservices.socialmedia.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
}
