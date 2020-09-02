package com.miloraddjordjevic.ecommerce.repositories;

import com.miloraddjordjevic.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
