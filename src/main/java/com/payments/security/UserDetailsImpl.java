package com.payments.security;

import com.payments.entity.AbstractUser;
import com.payments.entity.role.Role;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetailsImpl implements UserDetails {

    private final AbstractUser user;
    private final Role role;
    
    public UserDetailsImpl(AbstractUser user, Role role){
        this.user = user;
        this.role = role;
    }
    
    @Override
    public String getUsername(){
        String username = user.getLogin();
        return username;
    }
    
    @Override
    public String getPassword() {
        String password = user.getPassword();
        return password;
    }
    
    @Override
    public Collection<GrantedAuthority> getAuthorities(){
        GrantedAuthority grantedAuthority = (GrantedAuthority) role;
        Collection<GrantedAuthority> grantedAuthorities = Collections.singleton(grantedAuthority);
        return grantedAuthorities;
    }
    
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    
    @Override
    public boolean isEnabled(){
        return true;
    }    
}
