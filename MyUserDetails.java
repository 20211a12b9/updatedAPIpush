package com.vms.medxbid.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {

    private int userId;
    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.active = user.isActive();
//        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_CUSTUSER"));
    }

    public MyUserDetails(DistUser distUser) {
        this.userId = distUser.getId();
        this.userName = distUser.getUserName();
        this.password = distUser.getPassword();
        this.active = true;
//        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_DISTUSER"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public String getUsername() {
        return userName;
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

    public int getUserId() {
        return userId;
    }
}