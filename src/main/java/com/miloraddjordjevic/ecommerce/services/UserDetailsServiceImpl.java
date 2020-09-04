package com.miloraddjordjevic.ecommerce.services;

import com.miloraddjordjevic.ecommerce.entities.User;
import com.miloraddjordjevic.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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
            List<GrantedAuthority> authorities = new ArrayList<>();
            SimpleGrantedAuthority user_authority = new SimpleGrantedAuthority("ROLE_USER");
            SimpleGrantedAuthority admin_authority = new SimpleGrantedAuthority("ROLE_ADMIN");
            authorities.add(user_authority);
            authorities.add(admin_authority);
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }
}
