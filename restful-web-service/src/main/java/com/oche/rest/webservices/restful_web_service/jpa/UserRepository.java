package com.oche.rest.webservices.restful_web_service.jpa;

import com.oche.rest.webservices.restful_web_service.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
