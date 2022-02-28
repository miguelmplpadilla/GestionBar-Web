package com.security;

import com.dao.BarDao;
import com.entity.Bar;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {
    private final BarDao barDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Bar bar;
        try {
            bar = barDao.getByUserName(username).get(0);
        } catch (Exception e) {
            throw new BadCredentialsException("Contraseña o usuario incorrectos");
        }

        return new MyUserDetails(bar);
    }
}
