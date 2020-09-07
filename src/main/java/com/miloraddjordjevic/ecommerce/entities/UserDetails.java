package com.miloraddjordjevic.ecommerce.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final String ROLE_PREFIX = "ROLE_";
    private final User user;

    public UserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Roles roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (roles.getName().equals("USER")) {
            SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority(ROLE_PREFIX + "USER");
            authorities.add(userAuthority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
