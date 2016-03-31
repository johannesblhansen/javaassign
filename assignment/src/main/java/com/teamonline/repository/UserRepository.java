package com.teamonline.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teamonline.model.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

	AppUser findByUsername(String username);

}
