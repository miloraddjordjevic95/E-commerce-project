package com.miloraddjordjevic.ecommerce.services;

import com.miloraddjordjevic.ecommerce.entities.User;
import com.miloraddjordjevic.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = null;

        for (User u : userRepository.findAll()
        ) {
            if (u.getUsername().equals(s)) {
                user = u;
                break;
            }
        }
        if (user == null) {
            throw new UsernameNotFoundException("User with this username wasn't found!");
        } else {
            com.miloraddjordjevic.ecommerce.entities.UserDetails userDetails = new com.miloraddjordjevic.ecommerce.entities.UserDetails(user);
            org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User(userDetails.getUsername(), userDetails.getPassword(), userDetails.isEnabled(),
                    userDetails.isAccountNonExpired(), userDetails.isCredentialsNonExpired(), userDetails.isAccountNonLocked(), userDetails.getAuthorities());
            return u;
        }
    }
}
