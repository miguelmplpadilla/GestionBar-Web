package com.security;

import com.dao.BarDao;
import com.entity.Bar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class MyUserDetails implements UserDetails {
    Bar bar;

    public MyUserDetails(Bar bar) {
        this.bar = bar;
    }

    @Override
    public Collection getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_SENSEI"));
    }

    @Override
    public String getPassword() {
        return bar.getContrasena();
    }

    @Override
    public String getUsername() {
        return bar.getNombre();
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
